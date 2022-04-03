package com.boitdroid.userbackendapi.service;

import com.boitdroid.userbackendapi.data.models.Role;
import com.boitdroid.userbackendapi.data.models.User;
import com.boitdroid.userbackendapi.data.payloads.request.UserSignupRequest;
import com.boitdroid.userbackendapi.data.repository.RoleRepository;
import com.boitdroid.userbackendapi.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserSignupRequest userSignupRequest) {
        User user = new User();
        user.setName(userSignupRequest.getName());
        user.setUsername(userSignupRequest.getUsername());
        user.setEmail(userSignupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userSignupRequest.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);
        return user;
    }
}
