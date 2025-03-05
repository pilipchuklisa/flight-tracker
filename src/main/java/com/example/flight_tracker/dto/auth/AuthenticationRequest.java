package com.example.flight_tracker.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotNull(message = "user.email.not-blank")
    @Email(message = "user.email.is-valid")
    private String email;

    @NotBlank(message = "user.password.not-blank")
    private String password;
}
