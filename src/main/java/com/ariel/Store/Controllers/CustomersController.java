package com.ariel.Store.Controllers;

import com.ariel.Store.Dao.CustomersDao;
import com.ariel.Store.Models.Customers;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param response HttpServletResponse
     * @return List of Customers
     */
    @GetMapping()
    public List<Customers> getAllCustomers(@RequestHeader("dni") String dni, HttpServletResponse response) {
        return this.customersDao.getCustomers(dni, response);
    }

    /**
     *
     * @param customer Customers
     * @param dni  String
     * @param productName String
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    @PostMapping()
    public Map<String, String> soldProduct(@RequestBody Customers customer, @RequestHeader("dni") String dni, @RequestHeader("product_name") String productName, HttpServletResponse response){
        return this.customersDao.soldProduct(productName, dni, customer, response);
    }

    /**
     *
     * @param id integer
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    @PutMapping()
    public Map<String, String> payingDebt(@RequestHeader("id") int id, HttpServletResponse response){
        return this.customersDao.payingDebt(id, response);
    }
}
