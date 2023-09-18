package com.phuthanh.SpringSecurity_JWT_Token.services;

import com.phuthanh.SpringSecurity_JWT_Token.config.jwt.JwtService;
import com.phuthanh.SpringSecurity_JWT_Token.entities.Role;
import com.phuthanh.SpringSecurity_JWT_Token.entities.User;
import com.phuthanh.SpringSecurity_JWT_Token.entities.AuthenticationResponse;
import com.phuthanh.SpringSecurity_JWT_Token.repositories.UserRepository;
import com.phuthanh.SpringSecurity_JWT_Token.payloads.requests.AuthenticationRequest;
import com.phuthanh.SpringSecurity_JWT_Token.payloads.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        //Create new user
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        //Generate JWT for new user registered successfully
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        //authenticate
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        //find information of user in DB
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(); // if it doesn't find => throw exception
        //If authenticate successfully => Create JWT for this user
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
