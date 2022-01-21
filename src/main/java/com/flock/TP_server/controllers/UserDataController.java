package com.flock.TP_server.controllers;

import com.flock.TP_server.models.User;
import com.flock.TP_server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDataController {

    @Autowired
    private UserService userService;

    @GetMapping("/userData")
    public User getUserData(@RequestAttribute Integer userId) {
        return userService.getUserData(userId);
    }

}
