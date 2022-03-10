package com.ariel.Store.DaoImp;

import com.ariel.Store.Repositories.UsersRepository;
import com.ariel.Store.Dao.CustomersDao;
import com.ariel.Store.Models.Customers;
import com.ariel.Store.Models.Products;
import com.ariel.Store.Repositories.CustomersRepository;
import com.ariel.Store.Repositories.ProductsRepository;
import com.ariel.Store.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Map<String, String> serverResponse;

    private Optional<Users> usersValidate;

    @Override
    public Map<String, String> soldProduct(String productName,String userDni, Customers customer, HttpServletResponse response) {
        //status(Customers attribute) determines if the product is payed or not
        this.serverResponse = new HashMap<>();
        if(customer.isEmpty() || productName == null || userDni == null){
            response.setStatus(400);
            this.serverResponse.put("message", "Incomplete data");
            return this.serverResponse;
        }
        this.usersValidate = this.usersRepository.findByDni(userDni);
        if(this.usersValidate.isEmpty()){
            response.setStatus(400);
            this.serverResponse.put("message", "The user doesn't exits");
            return this.serverResponse;
        }
        customer.setUser(this.usersValidate.get());
        Optional<Products> productValidate = this.productsRepository.findByName(productName);
        if(productValidate.isEmpty()){
            response.setStatus(400);
            this.serverResponse.put("message", "The product doesn't exits");
            return this.serverResponse;
        }
        customer.setProductsBought(productValidate.get());
        if(customer.validateStock()){
            response.setStatus(400);
            this.serverResponse.put("message", "There's no much stock of that product");
            return this.serverResponse;
        }
        if(customer.getProductsBought().getStock() == 0){
            customer.getProductsBought().setState(false);
            this.productsRepository.save(customer.getProductsBought());
        }
        customer.setPrice(productValidate.get().getPrice() * customer.getItems());
        this.customersRepository.save(customer);
        this.serverResponse.put("message", "bought "+productValidate.get().getName());
        return this.serverResponse;
    }

    @Override
    public List<Customers> getCustomers(String dni, HttpServletResponse response) {
        if(dni == null){
            response.setStatus(400);
            return null;
        }
        this.usersValidate = this.usersRepository.findByDni(dni);
        if(this.usersValidate.isEmpty()){
            response.setStatus(400);
            return null;
        }
        return this.customersRepository.findByUser(this.usersValidate.get());
    }

    @Override
    public Map<String, String> payingDebt(int id, HttpServletResponse response) {
        this.serverResponse = new HashMap<>();
        if(id == 0){
            response.setStatus(400);
            this.serverResponse.put("message", "Incomplete data");
            return this.serverResponse;
        }
        Optional<Customers> customersValidate = this.customersRepository.findById(id);
        if(customersValidate.isEmpty()){
            response.setStatus(400);
            this.serverResponse.put("message", "There's no customer");
            return this.serverResponse;
        }
        if(customersValidate.get().isState()){
            response.setStatus(400);
            this.serverResponse.put("message", "This customer paid for this product");
            return this.serverResponse;
        }
        customersValidate.get().setState(true);
        this.customersRepository.save(customersValidate.get());
        this.serverResponse.put("message", "this customer now paid his debt");
        return this.serverResponse;
    }
}
