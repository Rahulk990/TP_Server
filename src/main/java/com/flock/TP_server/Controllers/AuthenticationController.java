package com.flock.TP_server.Controllers;

import com.flock.TP_server.Models.AuthToken;
import com.flock.TP_server.Models.User;
import com.flock.TP_server.Repositories.AuthTokenRepository;
import com.flock.TP_server.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @PostMapping("/login")
    public String loginUser(@RequestBody User user)
    {
        boolean isPresent = userRepository.checkCredentials(user);
        if(!isPresent){
            return "Incorrect Email or Password";
        }

        User userDetails = userRepository.getUserByEmail(user.getEmail());

        AuthToken authToken = new AuthToken(userDetails.getUserId());
        authTokenRepository.insertAuthToken(authToken);
        return authToken.getToken();
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user)
    {
        boolean isSuccess = userRepository.insertUser(user);
        if(!isSuccess){
            return "Email Already Exists";
        }

        User userDetails = userRepository.getUserByEmail(user.getEmail());

        AuthToken authToken = new AuthToken(userDetails.getUserId());
        authTokenRepository.insertAuthToken(authToken);
        return authToken.getToken();
    }
}
