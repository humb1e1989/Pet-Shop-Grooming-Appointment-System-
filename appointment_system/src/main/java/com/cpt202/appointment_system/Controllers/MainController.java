package com.cpt202.appointment_system.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    
    @GetMapping(value="/")
    public String home() {
        return "home";
    }
    
}
