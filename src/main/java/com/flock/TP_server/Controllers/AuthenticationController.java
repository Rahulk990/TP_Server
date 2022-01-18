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
    UserRepository userRepository;

    @Autowired
    AuthTokenRepository authTokenRepository;

    @PostMapping("/login")
    public String loginUser(@RequestBody User user)
    {
        // Checks the Credentials in the Database
        boolean isPresent = userRepository.checkCredentials(user);
        if(!isPresent){
            return "Incorrect Email or Password";
        }

        // Fetches the User Details
        User userDetails = userRepository.getUserByEmail(user.getEmail());

        // Generates and return Token
        AuthToken authToken = new AuthToken(userDetails.getUserId());
        authTokenRepository.insertAuthToken(authToken);
        return authToken.getToken();
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user)
    {
        // Check the Credentials in the Database
        boolean isSuccess = userRepository.insertUser(user);
        System.out.println(isSuccess);
        if(!isSuccess){
            return "Email Already Exists";
        }

        // Fetches the User Details
        User userDetails = userRepository.getUserByEmail(user.getEmail());

        // Generates and return Token
        AuthToken authToken = new AuthToken(userDetails.getUserId());
        authTokenRepository.insertAuthToken(authToken);
        return authToken.getToken();
    }
}
