package com.example.flight_tracker.api.models.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;
}
