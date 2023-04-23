package com.cpt202.appointment_system.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Services.GroomerService;

@Controller
public class mainController {

    @Autowired
    private GroomerService groomerService;
    
    // @GetMapping("/")
    // public String home(Model model) {
    //     model.addAttribute("user", new User());
    //     return "home";// because it is a controller, the springboot will pull a file from the
    //                   // resources
    // }

    // // http://localhost:8080/
    // @PostMapping("/")
    // public String confirmHome(@ModelAttribute("user") User user) {
    //     LoginController.loginUser(user.getUsername(), user.getPassword());
    //     return "home";// because it is a controller, the springboot will pull a file from the
    //                   // resources
    // }


    // http://localhost:8080/
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("username", "Default");
        return "home";// because it is a controller, the springboot will pull a file from the
                      // resources
    }

    // // http://localhost:8080/
    // @GetMapping()
    // public String homePage(Model model) {


    //     // render groomer data on the home page.
    //     List<Groomer> gList = groomerService.listAllGroomers();
        
    //     if (gList.size() < 4) {
    //         Groomer groomer = new Groomer();
    //         groomer.setImageURL("/assets/images/no-user.png");
    //         groomer.setName("No Groomer");
    //         groomer.setDescription("------------------------------------");
    //         model.addAttribute("g1", groomer);
    //         model.addAttribute("g2", groomer);
    //         model.addAttribute("g3", groomer);
    //         model.addAttribute("g4", groomer);
    //         return "Home";
    //     }

    //     model.addAttribute("g1", gList.get(0));
    //     model.addAttribute("g2", gList.get(1));
    //     model.addAttribute("g3", gList.get(2));
    //     model.addAttribute("g4", gList.get(3));
    //     return "home";
    // }

}
