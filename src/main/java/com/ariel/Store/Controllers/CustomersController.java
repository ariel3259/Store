package com.ariel.Store.Controllers;

import com.ariel.Store.Dao.CustomersDao;
import com.ariel.Store.Models.Customers;
import com.ariel.Store.Utils.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class CustomersController {

    @Autowired
    private CustomersDao customersDao;

    @Autowired
    private Tokens jwt;

    private Map<String, Object> serverError;

    /**
     *
     * @param dni String
     * @return ResponseEntity with List of Customers
     */
    @GetMapping()
    public ResponseEntity<List<Customers>> getAllCustomers(@RequestHeader("dni") String dni, @RequestHeader("authorization") String token) {
        if(!jwt.verificationToken(token, dni)){
            return ResponseEntity.status(401).body(null);
        }
        return this.customersDao.getCustomers(dni);
    }

    /**
     *
     * @param customer Customers
     * @param dni  String
     * @param productId integer
     * @return ResponseEntity with Map of String/Object
     */
    @PostMapping()
    public ResponseEntity<Map<String, Object>> soldProduct(@RequestBody Customers customer, @RequestHeader("dni") String dni, @RequestHeader("product_id") int productId, @RequestHeader("authorization") String token){
        this.serverError = new HashMap<>();
        if(!jwt.verificationToken(token, dni)){
            this.serverError.put("message", "You're no logged in");
            return ResponseEntity.status(401).body(this.serverError);
        }
        return this.customersDao.soldProduct(productId, dni, customer);
    }

    /**
     *
     * @param id integer
     * @return Map of String/String
     */
    @PutMapping()
    public ResponseEntity<Map<String, Object>> payingDebt(@RequestHeader("id") int id, @RequestHeader(name = "dni") String dni, @RequestHeader("authorization") String token){
        this.serverError = new HashMap<>();
        if(!jwt.verificationToken(token, dni)){
            this.serverError.put("message", "You're no logged in");
            return ResponseEntity.status(401).body(this.serverError);
        }
        return this.customersDao.payingDebt(id);
    }
}
