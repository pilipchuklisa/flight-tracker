package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mysql.User;
import com.example.flight_tracker.dto.user.UserDto;
import com.example.flight_tracker.exceptions.ResourceNotFoundException;
import com.example.flight_tracker.repositories.mysql.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private static final String EMAIL = "email@gmail.com";

    @Test
    void findUserByEmail() {
        User user = new User();
        user.setEmail(EMAIL);

        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        UserDto foundUserDto = userService.findUserByEmail(EMAIL);

        Assertions.assertSame(user.getEmail(), foundUserDto.getEmail());
        Mockito.verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void findUserByEmailNotFound() {
        Mockito.when(userRepository.findByEmail(anyString())).thenThrow(ResourceNotFoundException.class);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.findUserByEmail(EMAIL));
    }
}