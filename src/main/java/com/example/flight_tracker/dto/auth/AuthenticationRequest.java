package com.example.flight_tracker.dto.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;
}
