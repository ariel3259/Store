package com.ariel.Store.DaoImp;


import com.ariel.Store.Dao.UsersDao;
import com.ariel.Store.Utils.Tokens;
import com.ariel.Store.Models.Users;
import com.ariel.Store.Repositories.UsersRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UsersDaoImp implements UsersDao {

    @Autowired
    private UsersRepository usersRepository;

    private Map<String, Object> serverResponse;

    @Override
    public ResponseEntity<Map<String, Object>> Register(Users user) {
        this.serverResponse = new HashMap<>();

        //Incomplete data
        if(user.isEmpty()) {
            this.serverResponse.put("message", "Incomplete data");
            return ResponseEntity.status(400).body(this.serverResponse);
        }

        //Invalid Email
        if(!user.isAValidEmail()){
            this.serverResponse.put("message", "Invalid Email");
            return ResponseEntity.status(400).body(this.serverResponse);
        }

        //Hashing password
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));

        //Saving user
        this.usersRepository.save(user);

        //Response
        this.serverResponse.put("message", "Congratulations, your account has been created");
        return ResponseEntity.status(200).body(this.serverResponse);
    }

    @Override
    public ResponseEntity<Map<String, Object>> Auth(Users user) {
        Tokens jwt = new Tokens();
        this.serverResponse = new HashMap<>();

        //Incomplete data
        if(user.getEmail() == null || user.getPassword() == null){
            this.serverResponse.put("message", "Incomplete data");
            return ResponseEntity.status(400).body(this.serverResponse);
        }

        //Invalid Email
        if(!user.isAValidEmail()){
            this.serverResponse.put("message", "Invalid Email");
            return ResponseEntity.status(400).body(this.serverResponse);
        }
        Optional<Users> validatesUser = this.usersRepository.findByEmail(user.getEmail());

        //User doesn't exits
        if(validatesUser.isEmpty()){
            this.serverResponse.put("message", "The  user doesn't exits");
            return ResponseEntity.status(401).body(this.serverResponse);
        }

        //Wrong password
        if(!BCrypt.checkpw(user.getPassword(), validatesUser.get().getPassword())){
            this.serverResponse.put("message", "Wrong password");
            return ResponseEntity.status(401).body(this.serverResponse);
        }

        //Response
        this.serverResponse.put("message", "Welcome "+validatesUser.get().getName());
        this.serverResponse.put("dni", validatesUser.get().getDni());     //dni also works as subject for jwt
        this.serverResponse.put("name", validatesUser.get().getName());
        this.serverResponse.put("lastName", validatesUser.get().getLastName());
        this.serverResponse.put("access_token", jwt.getToken(validatesUser.get().getDni()));
        return ResponseEntity.status(200).body(this.serverResponse);
    }
}
