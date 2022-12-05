package com.boitdroid.userbackendapi.data.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse {

    private Boolean success;
    private String message;
}
