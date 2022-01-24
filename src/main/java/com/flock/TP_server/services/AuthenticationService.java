package com.flock.TP_server.services;

import com.flock.TP_server.exception.BadRequestException;
import com.flock.TP_server.exception.ResourceNotFoundException;
import com.flock.TP_server.models.AuthToken;
import com.flock.TP_server.models.User;
import com.flock.TP_server.repositories.AuthTokenRepository;
import com.flock.TP_server.repositories.UserRepository;
import com.flock.TP_server.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Set;

@Service
@Validated
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


    public String loginUser(@Valid User user) {
        String passwordHash = StringUtils.generateHashForString(user.getPassword());
        user.setPassword(passwordHash);
        boolean isPresent = userRepository.checkCredentials(user);
        if (!isPresent) {
            throw new ResourceNotFoundException("Incorrect Email or Password");
        }

        User userDetails = userService.getUserData(user.getEmail());
        return generateToken(userDetails);
    }

    public String registerUser(@Valid User user) {
        if(isFullNameNullOrBlank(user)) {
            throw new BadRequestException("FullName should be empty");
        }
        String passwordHash = StringUtils.generateHashForString(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.insertUser(user);
        User userDetails = userRepository.getUserByEmail(user.getEmail());
        return generateToken(userDetails);
    }

    public static boolean isFullNameNullOrBlank(User user) {
        if(user.getFullName() == null || user.getFullName().trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkToken(String token) {
        return authTokenRepository.checkToken(token);
    }

    public Integer getUserId(String token) {
        AuthToken authToken = authTokenRepository.getAuthTokenByToken(token);
        return authToken.getUserId();
    }
}
