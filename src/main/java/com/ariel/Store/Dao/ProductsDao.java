package com.ariel.Store.Dao;

import com.ariel.Store.Models.Products;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ProductsDao {
    List<Products> GetAllProducts(String dni,boolean state, HttpServletResponse response);
    Map<String, String> AddOneProduct(Products product, String dni, HttpServletResponse response);
    Map<String, String> ModifiedOneProduct(Products product, HttpServletResponse response);
    Map<String, String> DeletedOneProduct(int id, HttpServletResponse response);
}
