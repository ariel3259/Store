package com.ariel.Store.DaoImp;

import com.ariel.Store.Dao.ProductsDao;
import com.ariel.Store.Models.Products;
import com.ariel.Store.Models.Users;
import com.ariel.Store.Repositories.ProductsRepository;
import com.ariel.Store.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.servlet.http.HttpServletResponse;
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

    private Map<String, String> serverResponse;

    private Optional<Users> userValidate;

    @Override
    public List<Products> GetAllProducts(String dni, boolean state, HttpServletResponse response) {
        if(dni == null){
            response.setStatus(400);
            return null;
        }
        this.userValidate = this.usersRepository.findByDni(dni);
        if(this.userValidate.isEmpty()){
            response.setStatus(400);
            return null;
        }
        return this.productsRepository.findByStateAndUser(state, this.userValidate.get());
    }

    @Override
    public Map<String, String> AddOneProduct(Products product, String dni, HttpServletResponse response) {
        this.serverResponse = new HashMap<>();
        if(product.isEmpty() || dni == null){
            response.setStatus(400);
            this.serverResponse.put("message", "Incomplete data");
            return this.serverResponse;
        }
        this.userValidate = this.usersRepository.findByDni(dni);
        if(this.userValidate.isEmpty()){
            response.setStatus(400);
            this.serverResponse.put("message", "The user doesn't exits");
            return this.serverResponse;
        }
        product.setUser(this.userValidate.get());
        product.setState(true);
        this.productsRepository.save(product);
        this.serverResponse.put("message", "Saved product");
        return this.serverResponse;
    }

    @Override
    public Map<String, String> ModifiedOneProduct(Products product, HttpServletResponse response) {
        this.serverResponse = new HashMap<>();
        if(product.isEmpty() || product.getId() == 0){
            response.setStatus(400);
            this.serverResponse.put("message", "Incomplete data");
            return this.serverResponse;
        }
        this.productsRepository.save(product);
        this.serverResponse.put("message", "Modified product");
        return this.serverResponse;
    }

    @Override
    public Map<String, String> DeletedOneProduct(int id, HttpServletResponse response) {
        this.serverResponse = new HashMap<>();
        if(id == 0){
            response.setStatus(400);
            this.serverResponse.put("message", "Incomplete data");
            return this.serverResponse;
        }
        Optional<Products> validateProduct = this.productsRepository.findById(id);
        if(validateProduct.isEmpty()){
            response.setStatus(400);
            this.serverResponse.put("message", "The product doesn't exits");
        }
        Products product = validateProduct.get();
        product.setState(false);
        this.productsRepository.save(product);
        this.serverResponse.put("message", "Erased Item");
        return this.serverResponse;
    }
}
