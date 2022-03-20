package com.ariel.Store.DaoImp;

import com.ariel.Store.Repositories.UsersRepository;
import com.ariel.Store.Dao.CustomersDao;
import com.ariel.Store.Models.Customers;
import com.ariel.Store.Models.Products;
import com.ariel.Store.Repositories.CustomersRepository;
import com.ariel.Store.Repositories.ProductsRepository;
import com.ariel.Store.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomersDaoImp implements CustomersDao {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private UsersRepository usersRepository;

    private Map<String, Object> serverResponse;

    private Optional<Users> usersValidate;

    @Override
    public ResponseEntity<Map<String, Object>> soldProduct(int productId, String userDni, Customers customer) {
        //status(Customers attribute) determines if the product is payed or not
        this.serverResponse = new HashMap<>();
        if(customer.isEmpty() || productId == 0 || userDni == null){
            this.serverResponse.put("message", "Incomplete data");
            return ResponseEntity.status(400).body(this.serverResponse);
        }
        this.usersValidate = this.usersRepository.findByDni(userDni);
        if(this.usersValidate.isEmpty()){
            this.serverResponse.put("message", "The user doesn't exits");
            return ResponseEntity.status(401).body(this.serverResponse);
        }
        customer.setUser(this.usersValidate.get());
        Optional<Products> productValidate = this.productsRepository.findById(productId);
        if(productValidate.isEmpty()){
            this.serverResponse.put("message", "The product doesn't exits");
            return ResponseEntity.status(400).body(this.serverResponse);
        }
        customer.setProductsBought(productValidate.get());
        if(customer.validateStock()){
            this.serverResponse.put("message", "There's no much stock of that product");
            return ResponseEntity.status(400).body(this.serverResponse);
        }
        if(customer.getProductsBought().getStock() == 0){
            customer.getProductsBought().setState(false);
            this.productsRepository.save(customer.getProductsBought());
        }
        customer.setPrice(productValidate.get().getPrice() * customer.getItems());
        this.customersRepository.save(customer);
        this.serverResponse.put("message", "bought "+productValidate.get().getName());
        return ResponseEntity.status(200).body(this.serverResponse);
    }

    @Override
    public ResponseEntity<List<Customers>> getCustomers(String dni) {
        if(dni == null){
            return ResponseEntity.status(400).body(null);
        }

        this.usersValidate = this.usersRepository.findByDni(dni);
        if(this.usersValidate.isEmpty()){

            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.status(200).body(this.customersRepository.findByUser(this.usersValidate.get()));
    }

    @Override
    public ResponseEntity<Map<String, Object>> payingDebt(int id) {
        this.serverResponse = new HashMap<>();
        if(id == 0){
            this.serverResponse.put("message", "Incomplete data");
            return ResponseEntity.status(400).body(this.serverResponse);
        }
        Optional<Customers> customersValidate = this.customersRepository.findById(id);
        if(customersValidate.isEmpty()){
            this.serverResponse.put("message", "There's no customer");
            return ResponseEntity.status(400).body(this.serverResponse);
        }
        if(customersValidate.get().isState()){
            this.serverResponse.put("message", "This customer paid for this product");
            return ResponseEntity.status(400).body(this.serverResponse);
        }
        customersValidate.get().setState(true);
        this.customersRepository.save(customersValidate.get());
        this.serverResponse.put("message", "this customer now paid his debt");
        return ResponseEntity.status(200).body(this.serverResponse);
    }
}
