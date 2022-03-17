package com.ariel.Store.Dao;

import com.ariel.Store.Models.Users;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UsersDao {

    /**
     *
     * @param user Users
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    Map<String, String> Register(Users user, HttpServletResponse response);

    /**
     *
     * @param user Users
     * @param response HttpServletResponse
     * @return Map of String/String
     */
    Map<String, String> Auth(Users user, HttpServletResponse response);
}
