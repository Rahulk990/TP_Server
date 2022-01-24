package com.flock.TP_server.controllers;

import com.flock.TP_server.models.Token;
import com.flock.TP_server.models.User;
import com.flock.TP_server.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public Token loginUser(@RequestBody User user) {
        return new Token(authenticationService.loginUser(user));
    }

    @PostMapping("/register")
    public Token registerUser(@RequestBody User user) {
        return new Token(authenticationService.registerUser(user));
    }
}
