package com.ariel.Store.Dao;

import com.ariel.Store.Models.Users;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UsersDao {

    /**
     *
     * @param user Users
     * @return ResponseEntity with Map String/Object
     */
    ResponseEntity<Map<String, Object>> Register(Users user);

    /**
     *
     * @param user Users
     * @return ResponseEntity with Map String/Object
     */
    ResponseEntity<Map<String, Object>> Auth(Users user);
}
