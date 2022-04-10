package com.boitdroid.userbackendapi.services;

import com.boitdroid.userbackendapi.models.Users;
import com.boitdroid.userbackendapi.payload.request.AuthRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {
    Users createAccount(AuthRequest newUser);
    Users loginAccount(AuthRequest loggedInUser);
    List<Users> getAllUsers();
    Users getUserById(long id);
    Users updateUserInfo(AuthRequest authRequest,long id);
    String deleteAccount(long id);

}
