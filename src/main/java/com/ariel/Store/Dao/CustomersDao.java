package com.ariel.Store.Dao;

import com.ariel.Store.Models.Customers;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface CustomersDao {

    /**
     *
     * @param productName String
     * @param userDni  String
     * @param customer Customers
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    Map<String, String> soldProduct(String productName,String userDni, Customers customer, HttpServletResponse response);

    /**
     *
     * @param dni String
     * @param response HttpServletResponse
     * @return List of Customers
     */
    List<Customers> getCustomers(String dni, HttpServletResponse response);

    /**
     *
     * @param id integer
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    Map<String, String> payingDebt(int id, HttpServletResponse response);

}
