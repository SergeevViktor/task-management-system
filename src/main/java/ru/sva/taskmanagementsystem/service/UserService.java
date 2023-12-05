package ru.sva.taskmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sva.taskmanagementsystem.dto.RegistrationUserDto;
import ru.sva.taskmanagementsystem.dto.UserDto;
import ru.sva.taskmanagementsystem.exception.ValidationException;
import ru.sva.taskmanagementsystem.model.User;
import ru.sva.taskmanagementsystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDto createUser(RegistrationUserDto registrationUserDto) {
        //TODO: добавить проверку на существование юзера
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new ValidationException("Пароли не совпадают!");
        }
        if (findByUsername(registrationUserDto.getUsername()).isPresent()) {
            throw new ValidationException("Пользователь с указанным уменем уже существует!");
        }
        User user = User.builder()
                .username(registrationUserDto.getUsername())
                .password(passwordEncoder.encode(registrationUserDto.getPassword()))
                .email(registrationUserDto.getEmail())
                .roles(List.of(roleService.findByName("ROLE_USER")))
                .build();
        userRepository.save(user);

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
