package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mysql.User;
import com.example.flight_tracker.dto.user.UserDto;
import com.example.flight_tracker.exceptions.ResourceNotFoundException;
import com.example.flight_tracker.mapper.UserMapper;
import com.example.flight_tracker.repositories.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User %s not found".formatted(email))
        );
        return userMapper.userToUserDto(user);
    }
}
