package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mysql.User;
import com.example.flight_tracker.dto.UserDto;
import com.example.flight_tracker.exceptions.ResourceNotFoundException;
import com.example.flight_tracker.repositories.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );

        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
