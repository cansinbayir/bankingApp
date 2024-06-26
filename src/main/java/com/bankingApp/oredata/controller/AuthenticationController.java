package com.bankingApp.oredata.controller;

import com.bankingApp.oredata.model.AuthenticationResponse;
import com.bankingApp.oredata.model.LoginRequest;
import com.bankingApp.oredata.model.UserDto;
import com.bankingApp.oredata.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto userDto) {
        AuthenticationResponse response = authenticationService.register(userDto);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public  ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        AuthenticationResponse authentication = authenticationService.authenticate(request);

        return  new ResponseEntity<>(authentication, HttpStatus.CREATED);
    }


}

