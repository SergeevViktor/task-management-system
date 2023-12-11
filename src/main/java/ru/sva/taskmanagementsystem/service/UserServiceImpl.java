package ru.sva.taskmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sva.taskmanagementsystem.dto.user.RegistrationUserDto;
import ru.sva.taskmanagementsystem.dto.user.UserDto;
import ru.sva.taskmanagementsystem.dto.mapper.UserMapper;
import ru.sva.taskmanagementsystem.exception.ValidationException;
import ru.sva.taskmanagementsystem.model.User;
import ru.sva.taskmanagementsystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleServiceImpl roleServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
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
                .roles(List.of(roleServiceImpl.findByName("ROLE_USER")))
                .build();
        userRepository.save(user);

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    @Override
    public UserDto getInfo(String username) {
        User user = userRepository.findByUsername(username).get();

        return UserMapper.toUserDto(user);
    }
}
