package com.ariel.Store.Dao;

import com.ariel.Store.Models.Products;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface ProductsDao {

    /**
     *
     * @param dni String
     * @param state boolean
     * @return ResponseEntity List of Products
     */
    ResponseEntity<List<Products>> GetAllProducts(String dni, boolean state);

    /**
     *
     * @param product Products
     * @param dni String
     * @return Map of String/String
     */
    ResponseEntity<Map<String, Object>> AddOneProduct(Products product, String dni);

    /**
     *
     * @param product Products
     * @param dni String
     * @return ResponseEntity Map of String/String
     */
    ResponseEntity<Map<String, Object>> ModifiedOneProduct(Products product, String dni);

    /**
     *
     * @param id integer
     * @return ResponseEntity Map of String/String
     */
    ResponseEntity<Map<String, Object>> DeletedOneProduct(Integer id);
}
