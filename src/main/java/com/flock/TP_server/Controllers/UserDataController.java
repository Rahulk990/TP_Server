package com.flock.TP_server.Controllers;

import com.flock.TP_server.Models.AuthToken;
import com.flock.TP_server.Models.User;
import com.flock.TP_server.Repositories.AuthTokenRepository;
import com.flock.TP_server.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDataController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthTokenRepository authTokenRepository;

    @GetMapping("/status")
    public String getStatus()
    {
        return "OK";
    }

    @GetMapping("/userData")
    public User getUserData(@RequestHeader("Authorization") String token)
    {
        AuthToken authToken = authTokenRepository.getAuthTokenByToken(token);
        return userRepository.getUserByUserId(authToken.getUserId());
    }

}
