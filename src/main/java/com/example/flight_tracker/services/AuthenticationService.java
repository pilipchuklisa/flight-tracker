package com.example.flight_tracker.services;

import com.example.flight_tracker.api.models.AuthenticationRequest;
import com.example.flight_tracker.api.models.AuthenticationResponse;
import com.example.flight_tracker.api.models.RegisterRequest;
import com.example.flight_tracker.domain.Role;
import com.example.flight_tracker.domain.User;
import com.example.flight_tracker.exceptions.UsernameTakenException;
import com.example.flight_tracker.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {

        if (isUsernameTaken(request.getUsername())) {
            throw new UsernameTakenException("Username \"" + request.getUsername() + "\" is already taken.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new UsernameTakenException("Username \"" + request.getUsername() + "\" is already taken."));
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    private boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
