package com.flock.TP_server.services;

import com.flock.TP_server.models.AuthToken;
import com.flock.TP_server.models.User;
import com.flock.TP_server.repositories.AuthTokenRepository;
import com.flock.TP_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    private String generateToken(User user) {
        AuthToken authToken = new AuthToken(user.getUserId());
        authTokenRepository.insertAuthToken(authToken);
        return authToken.getToken();
    }

    public String loginUser(User user) {
        boolean isPresent = userRepository.checkCredentials(user);
        if (!isPresent) {
            return "Incorrect Email or Password";
        }

        User userDetails = userService.getUserData(user.getEmail());
        return generateToken(userDetails);
    }

    public String registerUser(User user) {
        boolean isSuccess = userRepository.insertUser(user);
        if (!isSuccess) {
            return "Email Already Exists";
        }

        User userDetails = userService.getUserData(user.getEmail());
        return generateToken(userDetails);
    }

    public boolean checkToken(String token) {
        return authTokenRepository.checkToken(token);
    }

    public Integer getUserId(String token) {
        AuthToken authToken = authTokenRepository.getAuthTokenByToken(token);
        return authToken.getUserId();
    }
}
