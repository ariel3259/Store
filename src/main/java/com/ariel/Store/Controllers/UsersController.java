package com.ariel.Store.Controllers;

import com.ariel.Store.Dao.UsersDao;
import com.ariel.Store.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost")
public class UsersController {

    @Autowired
    private UsersDao usersDao;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Users user, HttpServletResponse response){
        return this.usersDao.Register(user, response);
    }

    @PostMapping("/auth")
    public Map<String, String> auth(@RequestBody Users user, HttpServletResponse response){
        return this.usersDao.Auth(user, response);
    }

}
