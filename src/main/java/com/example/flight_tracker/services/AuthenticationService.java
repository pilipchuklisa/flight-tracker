package com.example.flight_tracker.services;

import com.example.flight_tracker.api.models.auth.AuthenticationRequest;
import com.example.flight_tracker.api.models.auth.AuthenticationResponse;
import com.example.flight_tracker.api.models.auth.RegisterRequest;
import com.example.flight_tracker.api.models.auth.VerifyUserDto;
import com.example.flight_tracker.domain.mysql.Role;
import com.example.flight_tracker.domain.mysql.User;
import com.example.flight_tracker.exceptions.*;
import com.example.flight_tracker.repositories.mysql.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;

    public User register(RegisterRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));
        user.setEnable(false);

        sendVerificationEmail(user);

        return userRepository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));

        if (!user.isEnabled()) {
            throw new AccountNotVerifiedException("Account not verified. Please verify your account.");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public void verifyUser(VerifyUserDto input) {
        Optional<User> optionalUser = userRepository.findByEmail(input.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
                throw new VerificationCodeExpiredException("Verification code has expired");
            }

            if (user.getVerificationCode().equals(input.getVerificationCode())) {
                user.setEnable(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiresAt(null);
                userRepository.save(user);
            } else {
                throw new InvalidVerificationCodeException("Invalid verification code");
            }
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    public void resendVerificationCode(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.isEnabled()) {
                throw new AccountAlreadyVerifiedException("Account is already verified");
            }

            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpiresAt(LocalDateTime.now().plusHours(1));
            sendVerificationEmail(user);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    private void sendVerificationEmail(User user) {
        String subject = "Account Verification";
        String verificationCode = "VERIFICATION CODE " + user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            mailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            //TODO: Handle email sending exception
            e.printStackTrace();
        }
    }

    private String generateVerificationCode() {
        int code = new Random().nextInt(900000) + 100000;
        return String.valueOf(code);
    }
}
