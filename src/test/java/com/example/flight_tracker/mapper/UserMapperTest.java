package com.example.flight_tracker.mapper;

import com.example.flight_tracker.domain.mysql.User;
import com.example.flight_tracker.dto.user.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    private UserMapper userMapper;

    private static final String NAME = "name";
    private static final String EMAIL = "email@gmail.com";

    @BeforeEach
    void setUp() {
        userMapper = new UserMapperImpl();
    }

    @Test
    void userToUserDto() {
        User user = new User();
        user.setName(NAME);
        user.setEmail(EMAIL);

        UserDto userDto = userMapper.userToUserDto(user);

        assertNotNull(userDto);
        assertEquals(NAME, userDto.getName());
        assertEquals(EMAIL, userDto.getEmail());
    }
}