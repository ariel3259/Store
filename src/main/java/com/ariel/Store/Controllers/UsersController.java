package com.ariel.Store.Controllers;

import com.ariel.Store.Dao.UsersDao;
import com.ariel.Store.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3000"}, methods = {RequestMethod.POST})
public class UsersController {

    @Autowired
    private UsersDao usersDao;
    
    /**
     * 
     * @param user Users
     * @return ResponseEntity with Map String/Object
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Users user){
        return this.usersDao.Register(user);
    }
    
    /**
     * 
     * @param user Users
     * @return	EntityResponse with Map String/Object
     */

    @PostMapping("/auth")
    public ResponseEntity<Map<String, Object>> auth(@RequestBody Users user){
        return this.usersDao.Auth(user);
    }

}
