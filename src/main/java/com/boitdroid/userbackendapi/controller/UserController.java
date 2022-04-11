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
                return ResponseHandler.messageResponse("Account with username already exists", HttpStatus.MULTI_STATUS);
            }
            if (user.getEmail().equals(authRequest.getEmail())){
                return ResponseHandler.messageResponse("Account with email already exists", HttpStatus.MULTI_STATUS);
            }
        }
        try{
            Users newUser = authService.createAccount(authRequest);
            return ResponseHandler.messageResponse("Account created successfully", HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseHandler.messageResponse("Account creation failed", HttpStatus.MULTI_STATUS);
        }
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody AuthRequest authRequest) {
        List<Users> users = authService.getAllUsers();

        for (Users other : users) {
            if (other.getUsername().equals(authRequest.getUsername())) {
                return ResponseHandler.messageResponse("Logged in successfully",HttpStatus.OK);
            }
        }

        return ResponseHandler.messageResponse("Failed to login",HttpStatus.MULTI_STATUS);
    }
}

