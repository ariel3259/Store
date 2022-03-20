package com.ariel.Store.Controllers;

import com.ariel.Store.Dao.ProductsDao;
import com.ariel.Store.Models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3000"}, allowedHeaders = "*",   methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductsController {

    @Autowired
    private ProductsDao productsDao;

    /**
     *
     * @param dni String
     * @param state boolean
     * @return ResponseEntity with List of Products
     */
    @GetMapping()
    public ResponseEntity<List<Products>> GetAllProducts(@RequestHeader(name = "dni") String dni, @RequestHeader(name = "state") boolean state) {
        return this.productsDao.GetAllProducts(dni, state);
    }

    /**
     *
     * @param product Products
     * @param dni String
     * @return ResponseEntity with Map of String/Object
     */
    @PostMapping()
    public ResponseEntity<Map<String, Object>> SaveOneProduct(@RequestBody Products product, @RequestHeader(name = "dni") String dni){
        return this.productsDao.AddOneProduct(product, dni);
    }

    /**
     *
     * @param product Products
     * @param dni String
     * @return ResponseEntity with Map of String/Object
     */
    @PutMapping()
    public ResponseEntity<Map<String, Object>> ModifiedOneProduct(@RequestBody Products product, @RequestHeader(name = "dni") String dni){
        return this.productsDao.ModifiedOneProduct(product,dni);
    }

    /**
     *
     * @param id integer
     * @return ResponseEntity with Map of String/Object
     */
    @DeleteMapping()
    public ResponseEntity<Map<String, Object>> DeletedOneProduct(@RequestHeader(name = "id") Integer id){
        return this.productsDao.DeletedOneProduct(id);
    }

}
