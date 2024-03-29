package com.boitdroid.userbackendapi.services;

import com.boitdroid.userbackendapi.data.models.Users;
import com.boitdroid.userbackendapi.data.payload.request.LoginRequest;
import com.boitdroid.userbackendapi.data.payload.request.SignupRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsersService {

    Object createAccount(SignupRequest signupRequest);
    String accountLogin(LoginRequest loginRequest);
    void accountDelete(Long id);
    List<Users> allUsers();
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsername(String username);
    Optional<Users> findByResetToken(String resetToken);
    void saveUser(Users user);
}
