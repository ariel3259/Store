package com.ariel.Store.DaoImp;

import com.ariel.Store.Dao.ProductsDao;
import com.ariel.Store.Models.Products;
import com.ariel.Store.Models.Users;
import com.ariel.Store.Repositories.ProductsRepository;
import com.ariel.Store.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductsDaoImp implements ProductsDao {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private UsersRepository usersRepository;

    private Map<String, Object> serverResponse;

    private Optional<Users> userValidate;

    @Override
    public ResponseEntity<List<Products>> GetAllProducts(String dni, boolean state) {

        //Invalid data
       if(dni == null){
            return ResponseEntity.status(400).body(null);
        }

       //Find User
        this.userValidate = this.usersRepository.findByDni(dni);

       //User doesn't exits
       if(this.userValidate.isEmpty()){
            return ResponseEntity.status(400).body(null);
       }

       //Response
        return ResponseEntity.status(200).body(this.productsRepository.findByStateAndUser(state, this.userValidate.get()));
    }

    @Override
    public ResponseEntity<Map<String, Object>> AddOneProduct(Products product, String dni) {
        this.serverResponse = new HashMap<>();

        //Incomplete data
        if(product.isEmpty() || dni == null){
            this.serverResponse.put("message", "Incomplete data");
            return ResponseEntity.status(400).body(this.serverResponse);
        }

        //Find user
        this.userValidate = this.usersRepository.findByDni(dni);

        //User doesn't exits
        if(this.userValidate.isEmpty()){
            this.serverResponse.put("message", "The user doesn't exits");
            return ResponseEntity.status(401).body(this.serverResponse);
        }

        //Adding User and State to product
        product.setUser(this.userValidate.get());
        product.setState(true);

        //Saving product
        this.productsRepository.save(product);

        //Response
        this.serverResponse.put("message", "Saved product");
        return ResponseEntity.status(200).body(this.serverResponse);
    }

    @Override
    public ResponseEntity<Map<String, Object>> ModifiedOneProduct(Products product, String dni) {
        this.serverResponse = new HashMap<>();

        //Incomplete data
        if(product.isEmpty() || product.getId() == 0 || dni == null){
            this.serverResponse.put("message", "Incomplete data");
            return ResponseEntity.status(400).body(this.serverResponse);
        }

        //Find user
        this.userValidate = this.usersRepository.findByDni(dni);

        //User doesn't exits
        if(this.userValidate.isEmpty()){
            this.serverResponse.put("message", "The user doesn't exits");
            return ResponseEntity.status(401).body(this.serverResponse);
        }

        //Adding User and State to product
        product.setState(true);
        product.setUser(this.userValidate.get());

        //save product
        this.productsRepository.save(product);

        //Response
        this.serverResponse.put("message", "Modified product");
        return ResponseEntity.status(200).body(this.serverResponse);
    }

    @Override
    public ResponseEntity<Map<String, Object>> DeletedOneProduct(Integer id) {
        this.serverResponse = new HashMap<>();

        //Incomplete data
        if(id == null){
            this.serverResponse.put("message", "Incomplete data");
            return ResponseEntity.status(400).body(this.serverResponse);
        }

        //Finding product to delete
        Optional<Products> validateProduct = this.productsRepository.findById(id);

        //Product doesn't exits
        if(validateProduct.isEmpty()){
            this.serverResponse.put("message", "The product doesn't exits");
            return ResponseEntity.status(400).body(this.serverResponse);
        }

        //Storages Product on Product variable
        Products product = validateProduct.get();

        //Deleting product
        product.setState(false);
        this.productsRepository.save(product);

        //Response
        this.serverResponse.put("message", "Erased Item");
        return ResponseEntity.status(200).body(this.serverResponse);
    }
}
