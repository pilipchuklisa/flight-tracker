package com.example.flight_tracker.controllers;

import com.example.flight_tracker.api.models.auth.AuthenticationRequest;
import com.example.flight_tracker.api.models.auth.AuthenticationResponse;
import com.example.flight_tracker.api.models.auth.RegisterRequest;
import com.example.flight_tracker.api.models.auth.VerifyUserDto;
import com.example.flight_tracker.domain.mysql.User;
import com.example.flight_tracker.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public User register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @GetMapping("/authentication")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

    @GetMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public String verify(@RequestBody VerifyUserDto verifyUserDto) {
        authenticationService.verifyUser(verifyUserDto);
        return "Account verified successfully";
    }

    @GetMapping("/resend")
    @ResponseStatus(HttpStatus.OK)
    public String resendVerificationCode(@RequestParam String email) {
        authenticationService.resendVerificationCode(email);
        return "Verification code sent";
    }
}
