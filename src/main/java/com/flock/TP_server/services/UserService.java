package com.flock.TP_server.services;

import com.flock.TP_server.models.User;
import com.flock.TP_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserData(int userId) {
        return userRepository.getUserByUserId(userId);
    }

    public User getUserData(String email) {
        return userRepository.getUserByEmail(email);
    }
}
