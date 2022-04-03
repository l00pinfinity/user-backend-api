package com.boitdroid.userbackendapi.service;

import com.boitdroid.userbackendapi.data.models.User;
import com.boitdroid.userbackendapi.data.payloads.request.UserSignupRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(UserSignupRequest userSignupRequest);

}
