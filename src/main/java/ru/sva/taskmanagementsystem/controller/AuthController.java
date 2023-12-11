package ru.sva.taskmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sva.taskmanagementsystem.dto.jwt.JwtRequest;
import ru.sva.taskmanagementsystem.dto.user.RegistrationUserDto;
import ru.sva.taskmanagementsystem.service.AuthServiceImpl;
import ru.sva.taskmanagementsystem.service.UserServiceImpl;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImpl userServiceImpl;
    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/authorization")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return ResponseEntity.ok(authServiceImpl.createAuthToken(authRequest));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return ResponseEntity.ok(userServiceImpl.createUser(registrationUserDto));
    }
}
