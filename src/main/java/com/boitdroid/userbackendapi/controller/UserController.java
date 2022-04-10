package com.boitdroid.userbackendapi.controller;

import com.boitdroid.userbackendapi.models.Users;
import com.boitdroid.userbackendapi.payload.request.AuthRequest;
import com.boitdroid.userbackendapi.payload.response.ResponseHandler;
import com.boitdroid.userbackendapi.repository.UserRepository;
import com.boitdroid.userbackendapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers(){
        List<Users> users = authService.getAllUsers();
        return ResponseHandler.response("Users available",HttpStatus.OK,users);
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody AuthRequest authRequest){
        List<Users> users = authService.getAllUsers();
        for (Users user:users){
            if (user.getUsername().equals(authRequest.getUsername())){
                return ResponseHandler.response("Account with username already exists", HttpStatus.MULTI_STATUS,null);
            }
        }
        try{
            Users newUser = authService.createAccount(authRequest);
            return ResponseHandler.response("Account created successfully", HttpStatus.CREATED,authRequest.getUsername());
        }catch (Exception e){
            return ResponseHandler.response("Account creation failed", HttpStatus.MULTI_STATUS,null);
        }
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody AuthRequest authRequest) {
        List<Users> users = authService.getAllUsers();

        for (Users other : users) {
            if (other.getUsername().equals(authRequest.getUsername())) {
                return ResponseHandler.response("Logged in successfully",HttpStatus.OK,authRequest.getUsername());
            }
        }

        return ResponseHandler.response("Failed to login",HttpStatus.MULTI_STATUS,null);
    }
}

