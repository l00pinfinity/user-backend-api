package com.boitdroid.userbackendapi.data.payloads.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserSignupRequest {
    private String name;
    private String username;
    private String email;
    private String password;

}
