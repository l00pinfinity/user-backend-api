package com.boitdroid.userbackendapi.controller;

import com.boitdroid.userbackendapi.data.models.Users;
import com.boitdroid.userbackendapi.data.payload.request.AuthRequest;
import com.boitdroid.userbackendapi.data.payload.response.ApiResponseHandler;
import com.boitdroid.userbackendapi.repository.UserRepository;
import com.boitdroid.userbackendapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers(){
        List<Users> users = authService.getAllUsers();
        return ApiResponseHandler.response("Users available",HttpStatus.OK,users);
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody AuthRequest authRequest){
        List<Users> users = authService.getAllUsers();
        for (Users user:users){
            if (user.getUsername().equals(authRequest.getUsername())){
                return ApiResponseHandler.messageResponse("Account with username already exists", HttpStatus.MULTI_STATUS);
            }
            if (user.getEmail().equals(authRequest.getEmail())){
                return ApiResponseHandler.messageResponse("Account with email already exists", HttpStatus.MULTI_STATUS);
            }
        }
        try{
            Users newUser = authService.createAccount(authRequest);
            return ApiResponseHandler.messageResponse("Account created successfully", HttpStatus.CREATED);
        }catch (Exception e){
            return ApiResponseHandler.messageResponse("Account creation failed", HttpStatus.MULTI_STATUS);
        }
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody AuthRequest authRequest) {
        List<Users> users = authService.getAllUsers();

        for (Users other : users) {
            if (other.getUsername().equals(authRequest.getUsername()) && other.getPassword().equals(authRequest.getPassword())) {
                return ApiResponseHandler.messageResponse("Logged in successfully",HttpStatus.OK);
            }
        }

        return ApiResponseHandler.messageResponse("Failed to login",HttpStatus.MULTI_STATUS);
    }
}

