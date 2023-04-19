package com.cpt202_group10.team.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Restful controller will return a object, but this is just a normal controller that will return a String
public class MainController {

    // http://localhost:8080/
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("username", "John");
        return "home";
    }
}
