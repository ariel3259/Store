package com.ariel.Store.Dao;

import com.ariel.Store.Models.Users;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UsersDao {
    Map<String, String> Register(Users user, HttpServletResponse response);
    Map<String, String> Auth(Users user, HttpServletResponse response);
}
