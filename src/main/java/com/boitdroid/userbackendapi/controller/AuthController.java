package com.boitdroid.userbackendapi.controller;

import com.boitdroid.userbackendapi.data.models.User;
import com.boitdroid.userbackendapi.data.payloads.request.UserRequest;
import com.boitdroid.userbackendapi.data.payloads.response.MessageResponse;
import com.boitdroid.userbackendapi.data.repository.UserRepository;
import com.boitdroid.userbackendapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/user/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> newUser(@Valid @RequestBody UserRequest request){
        try{
            User newUser = userService.registerUser(request);
            return MessageResponse.generatedResponse("User account created successfully", HttpStatus.CREATED,newUser);
        }catch (Exception ex){
            return MessageResponse.generatedResponse(ex.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }
}
