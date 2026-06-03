package com.space.sort.fiap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/astronaut-dashboard")
    public String astronautDashboard() {
        return "astronaut-dashboard";
    }

    @GetMapping("/civil-dashboard")
    public String civilDashboard() {
        return "civil-dashboard";
    }
}
