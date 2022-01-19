package com.flock.TP_server.Controllers;

import com.flock.TP_server.Models.AuthToken;
import com.flock.TP_server.Models.User;
import com.flock.TP_server.Repositories.AuthTokenRepository;
import com.flock.TP_server.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserDataController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/status")
    public String getStatus(@RequestAttribute int userId)
    {
        System.out.println(userId);
        return "OK";
    }

    @GetMapping("/userData")
    public User getUserData(@RequestAttribute int userId)
    {
        return userRepository.getUserByUserId(userId);
    }

}
