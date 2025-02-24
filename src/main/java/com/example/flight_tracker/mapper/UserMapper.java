package com.example.flight_tracker.mapper;

import com.example.flight_tracker.domain.mysql.User;
import com.example.flight_tracker.dto.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDto userToUserDto(User user);
}
