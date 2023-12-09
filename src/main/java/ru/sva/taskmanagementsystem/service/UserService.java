package ru.sva.taskmanagementsystem.service;

import ru.sva.taskmanagementsystem.dto.RegistrationUserDto;
import ru.sva.taskmanagementsystem.dto.UserDto;
import ru.sva.taskmanagementsystem.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long userId);

    UserDto createUser(RegistrationUserDto registrationUserDto);

    UserDto getInfo(String username);
}
