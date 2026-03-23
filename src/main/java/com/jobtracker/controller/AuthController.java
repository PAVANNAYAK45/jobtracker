package com.jobtracker.controller;

import com.jobtracker.entity.User;
import com.jobtracker.service.AuthService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import com.jobtracker.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = "")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
public String register(@Valid @RequestBody User user) {
    return authService.register(user.getEmail(), user.getPassword());
}

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request.getEmail(), request.getPassword());
    }
}