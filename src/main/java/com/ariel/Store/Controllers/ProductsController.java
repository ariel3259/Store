package com.ariel.Store.Controllers;

import com.ariel.Store.Dao.ProductsDao;
import com.ariel.Store.Models.Products;
import com.ariel.Store.Utils.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*",   methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ProductsController {

    @Autowired
    private ProductsDao productsDao;

    @Autowired
    private Tokens jwt;

    private Map<String, Object> serverError;

    /**
     *
     * @param dni String
     * @param state boolean
     * @param token String
     * @return ResponseEntity with List of Products
     */
    @GetMapping()
    public ResponseEntity<List<Products>> GetAllProducts(@RequestHeader(name = "dni") String dni, @RequestHeader(name = "state") boolean state, @RequestHeader("authorization") String token) {
        if(!jwt.verificationToken(token, dni)){
            return ResponseEntity.status(401).body(null);
        }
        return this.productsDao.GetAllProducts(dni, state);
    }

    /**
     *
     * @param product Products
     * @param dni String
     * @param token String
     * @return ResponseEntity with Map of String/Object
     */
    @PostMapping()
    public ResponseEntity<Map<String, Object>> SaveOneProduct(@RequestBody Products product, @RequestHeader(name = "dni") String dni, @RequestHeader("authorization") String token){
        this.serverError = new HashMap<>();
        if(!jwt.verificationToken(token, dni)){
            this.serverError.put("message", "You're no logged in");
            return ResponseEntity.status(401).body(this.serverError);
        }
        return this.productsDao.AddOneProduct(product, dni);
    }

    /**
     *
     * @param product Products
     * @param dni String
     * @param token String
     * @return ResponseEntity with Map of String/Object
     */
    @PutMapping()
    public ResponseEntity<Map<String, Object>> ModifiedOneProduct(@RequestBody Products product, @RequestHeader(name = "dni") String dni, @RequestHeader("authorization") String token){
        this.serverError = new HashMap<>();
        if(!jwt.verificationToken(token, dni)){
            this.serverError.put("message", "You're no logged in");
            return ResponseEntity.status(401).body(this.serverError);
        }
        return this.productsDao.ModifiedOneProduct(product,dni);
    }

    /**
     *
     * @param id integer
     * @param dni string
     * @param token String
     * @return ResponseEntity with Map of String/Object
     */
    @DeleteMapping()
    public ResponseEntity<Map<String, Object>> DeletedOneProduct(@RequestHeader(name = "id") Integer id, @RequestHeader(name = "dni") String dni, @RequestHeader("authorization") String token){
        this.serverError = new HashMap<>();
        if(!jwt.verificationToken(token, dni)){
            this.serverError.put("message", "You're no logged in");
            return ResponseEntity.status(401).body(this.serverError);
        }
        return this.productsDao.DeletedOneProduct(id);
    }

}
