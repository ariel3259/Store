package com.ariel.Store.Controllers;

import com.ariel.Store.Dao.CustomersDao;
import com.ariel.Store.Models.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class CustomersController {

    @Autowired
    private CustomersDao customersDao;

    /**
     *
     * @param dni String
     * @return ResponseEntity with List of Customers
     */
    @GetMapping()
    public ResponseEntity<List<Customers>> getAllCustomers(@RequestHeader("dni") String dni) {
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
    public ResponseEntity<Map<String, Object>> soldProduct(@RequestBody Customers customer, @RequestHeader("dni") String dni, @RequestHeader("product_id") int productId){
        return this.customersDao.soldProduct(productId, dni, customer);
    }

    /**
     *
     * @param id integer
     * @return Map of String/String
     */
    @PutMapping()
    public ResponseEntity<Map<String, Object>> payingDebt(@RequestHeader("id") int id){
        return this.customersDao.payingDebt(id);
    }
}
