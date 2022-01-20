package com.flock.TP_server.controllers;

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
    public String loginUser(@RequestBody User user) {
        return authenticationService.loginUser(user);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return authenticationService.registerUser(user);
    }
}
