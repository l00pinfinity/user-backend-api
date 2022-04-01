package com.boitdroid.userbackendapi.service;

import com.boitdroid.userbackendapi.data.models.User;
import com.boitdroid.userbackendapi.data.payloads.request.UserRequest;
import com.boitdroid.userbackendapi.exceptions.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registerUser(UserRequest userRequest);
}
