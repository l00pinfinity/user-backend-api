package com.boitdroid.userbackendapi.data.payloads.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserLoginRequest {
    private String usernameOrEmail;
    private String password;
}
