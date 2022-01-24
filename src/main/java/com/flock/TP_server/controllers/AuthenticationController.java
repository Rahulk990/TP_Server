package com.flock.TP_server.controllers;

import com.flock.TP_server.models.StringWrapper;
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
    public StringWrapper loginUser(@RequestBody User user) {
        return new StringWrapper(authenticationService.loginUser(user));
    }

    @PostMapping("/register")
    public StringWrapper registerUser(@RequestBody User user) {
        return new StringWrapper(authenticationService.registerUser(user));
    }
}
