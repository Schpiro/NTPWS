package com.NTPWS.projekt.controller;

import com.NTPWS.projekt.command.LoginCommand;
import com.NTPWS.projekt.model.dto.LoginDTO;
import com.NTPWS.projekt.service.interfaces.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("authentication")
@CrossOrigin(origins = "https://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public LoginDTO login(@Valid @RequestBody final LoginCommand command) {
        return authenticationService.login(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials"));
    }

    @PostMapping("/register")
    public LoginDTO register(@Valid @RequestBody final LoginCommand command) {
        return authenticationService.register(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials"));
    }

}
