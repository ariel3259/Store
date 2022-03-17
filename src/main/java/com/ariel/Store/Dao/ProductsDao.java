package com.ariel.Store.Dao;

import com.ariel.Store.Models.Products;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ProductsDao {

    /**
     *
     * @param dni String
     * @param state boolean
     * @param response HttpServlet Response
     * @return List of Products
     */
    List<Products> GetAllProducts(String dni,boolean state, HttpServletResponse response);

    /**
     *
     * @param product Products
     * @param dni String
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    Map<String, String> AddOneProduct(Products product, String dni, HttpServletResponse response);

    /**
     *
     * @param product Products
     * @param dni String
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    Map<String, String> ModifiedOneProduct(Products product, String dni, HttpServletResponse response);

    /**
     *
     * @param id integer
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    Map<String, String> DeletedOneProduct(int id, HttpServletResponse response);
}
