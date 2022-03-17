package com.ariel.Store.Controllers;

import com.ariel.Store.Dao.UsersDao;
import com.ariel.Store.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
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
     * @param response HttpServletResponse
     * @return Map of String to String
     */
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Users user, HttpServletResponse response){
        return this.usersDao.Register(user, response);
    }
    
    /**
     * 
     * @param user Users
     * @param response HttpServletResponse
     * @return	Map of String to String
     */

    @PostMapping("/auth")
    public Map<String, String> auth(@RequestBody Users user, HttpServletResponse response){
        return this.usersDao.Auth(user, response);
    }

}
