package com.example.flight_tracker.controllers.api;

import com.example.flight_tracker.dto.auth.AuthenticationRequest;
import com.example.flight_tracker.dto.auth.AuthenticationResponse;
import com.example.flight_tracker.dto.auth.RegisterRequest;
import com.example.flight_tracker.dto.auth.RegisterResponse;
import com.example.flight_tracker.services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationApiController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request,
                                               HttpServletResponse servletResponse) {
        AuthenticationResponse response = authenticationService.authenticate(request);

        Cookie cookie = new Cookie("jwt", response.getToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        servletResponse.addCookie(cookie);

        return response;
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @GetMapping("/verify")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void verify(@RequestParam String email, @RequestParam String code) {
        authenticationService.verifyUser(email, code);
    }

    @GetMapping("/resend")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resendVerificationCode(@RequestParam String email) {
        authenticationService.resendVerificationCode(email);
    }
}
