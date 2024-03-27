package com.bankingApp.oredata.service;


import com.bankingApp.oredata.model.LoginRequest;
import com.bankingApp.oredata.entity.User;
import com.bankingApp.oredata.model.AuthenticationResponse;
import com.bankingApp.oredata.model.UserDto;
import com.bankingApp.oredata.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(UserDto request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        // sign password
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        User registeredUser = userRepository.save(user);

        String jwtToken = jwtService.generateToken(registeredUser);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate (LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);

    }



}
