package com.boitdroid.userbackendapi.controller;

import com.boitdroid.userbackendapi.data.models.User;
import com.boitdroid.userbackendapi.data.payloads.request.UserLoginRequest;
import com.boitdroid.userbackendapi.data.payloads.request.UserSignupRequest;
import com.boitdroid.userbackendapi.data.payloads.response.MessageResponse;
import com.boitdroid.userbackendapi.data.repository.RoleRepository;
import com.boitdroid.userbackendapi.data.repository.UserRepository;
import com.boitdroid.userbackendapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserSignupRequest userSignupRequest){
        if (userRepository.existsByUsername(userSignupRequest.getUsername())){
            return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(userSignupRequest.getEmail())){
            return new ResponseEntity<>("Email is already in use", HttpStatus.BAD_REQUEST);
        }

        try{
            User newUser = userService.registerUser(userSignupRequest);
            return MessageResponse.response("User account created",HttpStatus.OK,newUser);
        }catch (Exception e){
            return MessageResponse.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }

    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody UserLoginRequest userLoginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getUsernameOrEmail(),userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User login successfuly",HttpStatus.OK);
    }
}
