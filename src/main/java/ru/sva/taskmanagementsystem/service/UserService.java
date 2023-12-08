package ru.sva.taskmanagementsystem.service;

import ru.sva.taskmanagementsystem.dto.RegistrationUserDto;
import ru.sva.taskmanagementsystem.dto.UserDto;
import ru.sva.taskmanagementsystem.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    UserDto createUser(RegistrationUserDto registrationUserDto);
}
