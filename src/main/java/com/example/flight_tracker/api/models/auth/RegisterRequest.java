package com.example.flight_tracker.api.models.auth;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
}
