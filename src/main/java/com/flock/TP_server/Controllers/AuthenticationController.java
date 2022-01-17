package com.flock.TP_server.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @PostMapping("/login")
    public String loginUser()
    {
        // Check the Credentials in the Database

        // Generate and return Token

        // Else return Error String
        // return "Incorrect Email or Password";


        return "Internal Server Error Occurred";
    }

    @PostMapping("/register")
    public String registerUser()
    {
        // Check the Email in the Database
        // return "Email Already Exists";

        // Store Data and Generate Token

        // Else return Error String


        return "Internal Server Error Occurred";
    }
}
