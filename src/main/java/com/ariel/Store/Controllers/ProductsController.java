package com.ariel.Store.Controllers;

import com.ariel.Store.Dao.ProductsDao;
import com.ariel.Store.JUtils.Tokens;
import com.ariel.Store.Models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost")
public class ProductsController {

    @Autowired
    private ProductsDao productsDao;

    private final Tokens jwt = new Tokens();

    private Map<String, String> err;

    @GetMapping()
    public List<Products> GetAllProducts(@RequestHeader(name = "dni") String dni,@RequestHeader(name = "state") boolean state, HttpServletResponse response) {
        return this.productsDao.GetAllProducts(dni, state, response);
    }

    @PostMapping()
    public Map<String, String> SaveOneProduct(@RequestBody Products product,@RequestHeader(name = "dni") String dni, HttpServletResponse response){
        return this.productsDao.AddOneProduct(product, dni, response);
    }

    @PutMapping()
    public Map<String, String> ModifiedOneProduct(@RequestBody Products product, HttpServletResponse response){
        return this.productsDao.ModifiedOneProduct(product, response);
    }

    @DeleteMapping()
    public Map<String, String> DeletedOneProduct(@RequestHeader(name = "id") String id, HttpServletResponse response){
        return this.productsDao.DeletedOneProduct(Integer.parseInt(id), response);
    }

}
