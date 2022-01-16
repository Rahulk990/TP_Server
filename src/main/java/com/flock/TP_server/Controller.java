package com.flock.TP_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/status")
    public String getStatus()
    {
        System.out.println("This is OK");
        return "OK";
    }
}
