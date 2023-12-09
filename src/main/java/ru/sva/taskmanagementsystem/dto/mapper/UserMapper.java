package ru.sva.taskmanagementsystem.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.sva.taskmanagementsystem.dto.UserDto;
import ru.sva.taskmanagementsystem.model.User;

@UtilityClass
public class UserMapper {
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();

        return  userDto;
    }
}
