package com.example.flight_tracker.api.models.auth;

import lombok.Data;

@Data
public class VerifyUserDto {

    private String email;
    private String verificationCode;
}
