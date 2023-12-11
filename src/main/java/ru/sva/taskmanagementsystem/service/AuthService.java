package ru.sva.taskmanagementsystem.service;

import ru.sva.taskmanagementsystem.dto.jwt.JwtRequest;
import ru.sva.taskmanagementsystem.dto.jwt.JwtResponse;

public interface AuthService {
    JwtResponse createAuthToken(JwtRequest authRequest);
}
