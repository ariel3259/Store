package com.ariel.Store.Controllers;

import com.ariel.Store.Dao.ProductsDao;
import com.ariel.Store.Models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3000"},   methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductsController {

    @Autowired
    private ProductsDao productsDao;

    /**
     *
     * @param dni String
     * @param state boolean
     * @param response HttpServletResponse
     * @return List of Products
     */
    @GetMapping()
    public List<Products> GetAllProducts(@RequestHeader(name = "dni") String dni,@RequestHeader(name = "state") boolean state, HttpServletResponse response) {
        return this.productsDao.GetAllProducts(dni, state, response);
    }

    /**
     *
     * @param product Products
     * @param dni String
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    @PostMapping()
    public Map<String, String> SaveOneProduct(@RequestBody Products product,@RequestHeader(name = "dni") String dni, HttpServletResponse response){
        return this.productsDao.AddOneProduct(product, dni, response);
    }

    /**
     *
     * @param product Products
     * @param dni String
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    @PutMapping()
    public Map<String, String> ModifiedOneProduct(@RequestBody Products product, @RequestHeader(name = "dni") String dni, HttpServletResponse response){
        return this.productsDao.ModifiedOneProduct(product,dni, response);
    }

    /**
     *
     * @param id integer
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    @DeleteMapping()
    public Map<String, String> DeletedOneProduct(@RequestHeader(name = "id") int id, HttpServletResponse response){
        return this.productsDao.DeletedOneProduct(id, response);
    }

}
