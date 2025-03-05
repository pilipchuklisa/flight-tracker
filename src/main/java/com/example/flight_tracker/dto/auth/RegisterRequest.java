package com.example.flight_tracker.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "user.name.not-blank")
    @Max(value = 100, message = "user.name.max")
    private String username;

    @NotBlank(message = "user.email.not-blank")
    @Email(message = "user.email.is-valid")
    private String email;

    @NotBlank(message = "user.password.not-blank")
    @Min(value = 8, message = "user.password.min")
    private String password;
}
