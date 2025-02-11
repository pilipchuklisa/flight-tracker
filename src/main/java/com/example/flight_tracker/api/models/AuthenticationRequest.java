package com.example.flight_tracker.api.models;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;
}
