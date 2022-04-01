package com.boitdroid.userbackendapi.service;

import com.boitdroid.userbackendapi.data.models.User;
import com.boitdroid.userbackendapi.data.payloads.request.UserRequest;
import com.boitdroid.userbackendapi.data.repository.UserRepository;
import com.boitdroid.userbackendapi.exceptions.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
        return user;
    }
}
