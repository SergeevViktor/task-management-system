package ru.sva.taskmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.sva.taskmanagementsystem.dto.JwtRequest;
import ru.sva.taskmanagementsystem.dto.JwtResponse;
import ru.sva.taskmanagementsystem.exception.AppError;
import ru.sva.taskmanagementsystem.util.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponse createAuthToken(JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
            ));
        } catch (BadCredentialsException exception) {
            throw new AppError("Некорректный логин/пароль!");
        }
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
