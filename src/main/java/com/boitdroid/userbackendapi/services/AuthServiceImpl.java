package com.boitdroid.userbackendapi.services;

import com.boitdroid.userbackendapi.models.Users;
import com.boitdroid.userbackendapi.payload.request.AuthRequest;
import com.boitdroid.userbackendapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users createAccount(AuthRequest newUser) {
        Users user = new Users();
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
        return user;
    }

    @Override
    public Users loginAccount(AuthRequest loggedInUser) {
        Users user = new Users();
        userRepository.save(user);
        return user;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Users updateUserInfo(AuthRequest authRequest, long id) {
        Users updateUser = userRepository.findById(id).get();
        updateUser.setUsername(authRequest.getUsername());
        updateUser.setPassword(authRequest.getPassword());
        userRepository.save(updateUser);
        return updateUser;
    }

    @Override
    public String deleteAccount(long id) {
        Users users = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return users.getUsername() + "deleted successfully";
    }
}
