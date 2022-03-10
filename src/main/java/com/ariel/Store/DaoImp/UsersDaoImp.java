package com.ariel.Store.DaoImp;


import com.ariel.Store.Dao.UsersDao;
import com.ariel.Store.JUtils.Tokens;
import com.ariel.Store.Models.Users;
import com.ariel.Store.Repositories.UsersRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UsersDaoImp implements UsersDao {

    @Autowired
    private UsersRepository usersRepository;

    private Map<String, String> serverResponse;

    @Override
    public Map<String, String> Register(Users user, HttpServletResponse response) {
        this.serverResponse = new HashMap<>();
        if(user.isEmpty()) {
            response.setStatus(400);
            this.serverResponse.put("message", "Incomplete data");
            return this.serverResponse;
        }
        if(!user.isAValidEmail()){
            response.setStatus(400);
            this.serverResponse.put("message", "Invalid Email");
            return this.serverResponse;
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        this.usersRepository.save(user);
        this.serverResponse.put("message", "Congratulations, your account has been created");
        return this.serverResponse;
    }

    @Override
    public Map<String, String> Auth(Users user, HttpServletResponse response) {
        Tokens jwt = new Tokens();
        this.serverResponse = new HashMap<>();
        if(user.getEmail() == null || user.getPassword() == null){
            response.setStatus(400);
            this.serverResponse.put("message", "Incomplete data");
            return this.serverResponse;
        }
        if(!user.isAValidEmail()){
            response.setStatus(400);
            this.serverResponse.put("message", "Invalid Email");
            return this.serverResponse;
        }
        Optional<Users> validatesUser = this.usersRepository.findByEmail(user.getEmail());
        if(validatesUser.isEmpty()){
            response.setStatus(400);
            this.serverResponse.put("message", "The  user doesn't exits");
            return this.serverResponse;
        }
        if(!BCrypt.checkpw(user.getPassword(), validatesUser.get().getPassword())){
            response.setStatus(400);
            this.serverResponse.put("message", "Wrong password");
            return this.serverResponse;
        }
        this.serverResponse.put("message", "Welcome "+validatesUser.get().getName());
        this.serverResponse.put("token", jwt.getToken(validatesUser.get().getId(), "http://localhost"));
        this.serverResponse.put("id", String.valueOf(validatesUser.get().getId()));
        return this.serverResponse;
    }
}
