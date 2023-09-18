package com.phuthanh.SpringSecurity_JWT_Token.service;

import com.phuthanh.SpringSecurity_JWT_Token.config.JwtService;
import com.phuthanh.SpringSecurity_JWT_Token.entity.Role;
import com.phuthanh.SpringSecurity_JWT_Token.entity.User;
import com.phuthanh.SpringSecurity_JWT_Token.entity.auth.AuthenticationResponse;
import com.phuthanh.SpringSecurity_JWT_Token.repository.UserRepository;
import com.phuthanh.SpringSecurity_JWT_Token.request.AuthenticationRequest;
import com.phuthanh.SpringSecurity_JWT_Token.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        return null;
    }
}
