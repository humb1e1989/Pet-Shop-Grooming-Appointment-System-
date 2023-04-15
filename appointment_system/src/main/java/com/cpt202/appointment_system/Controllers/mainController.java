package com.cpt202.appointment_system.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    // http://localhost:8080/
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("username", "Tomas");
        return "home";// because it is a controller, the springboot will pull a file from the
                      // resources
    }
}
