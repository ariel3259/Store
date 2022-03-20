package com.ariel.Store.Dao;

import com.ariel.Store.Models.Customers;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface CustomersDao {

    /**
     *
     * @param productId int
     * @param userDni  String
     * @param customer Customers
     * @return ResponseEntity with Map of String/String
     */
    ResponseEntity<Map<String, Object>> soldProduct(int productId, String userDni, Customers customer);

    /**
     *
     * @param dni String
     * @return ResponseEntity with List of Customers
     */
    ResponseEntity<List<Customers>> getCustomers(String dni);

    /**
     *
     * @param id integer
     * @return ResponseEntity with Map of String/String
     */
    ResponseEntity<Map<String, Object>> payingDebt(int id);

}
