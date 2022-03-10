package com.ariel.Store.Dao;

import com.ariel.Store.Models.Customers;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface CustomersDao {
    Map<String, String> soldProduct(String productName,String userDni, Customers customer, HttpServletResponse response);
    List<Customers> getCustomers(String dni, HttpServletResponse response);
    Map<String, String> payingDebt(int id, HttpServletResponse response);

}
