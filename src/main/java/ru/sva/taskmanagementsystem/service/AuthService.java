package ru.sva.taskmanagementsystem.service;

import ru.sva.taskmanagementsystem.dto.JwtRequest;
import ru.sva.taskmanagementsystem.dto.JwtResponse;

public interface AuthService {
    JwtResponse createAuthToken(JwtRequest authRequest);
}
