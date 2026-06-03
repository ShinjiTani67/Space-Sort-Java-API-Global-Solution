package com.space.sort.fiap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
    
}
