package com.ariel.Store.Controllers;

import com.ariel.Store.Dao.CustomersDao;
import com.ariel.Store.JUtils.Tokens;
import com.ariel.Store.Models.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    @Autowired
    private CustomersDao customersDao;

    private Map<String, String> err;

    @GetMapping()
    public List<Customers> getAllCustomers(@RequestHeader("dni") String dni, HttpServletResponse response, String subject) throws IOException {
        return this.customersDao.getCustomers(dni, response);
    }

    @PostMapping()
    public Map<String, String> soldProduct(@RequestBody Customers customer, @RequestHeader("dni") String dni, @RequestHeader("product_name") String productName, HttpServletResponse response){
        return this.customersDao.soldProduct(productName, dni, customer, response);
    }

    @PutMapping()
    public Map<String, String> payingDebt(@RequestHeader("id") int id, HttpServletResponse response){
        return this.customersDao.payingDebt(id, response);
    }
}
