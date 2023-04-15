package com.cpt202.appointment_system.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Services.GroomerService;

@Controller
public class TestController {
//SQZ And WJT
//Try to connect front end and back end
    @Autowired
    private GroomerService groomerService;
    // Manager Part
    @GetMapping("/manager/groomerList")  
    public String getAllGroomers(Model model){
        model.addAttribute("groomerList", groomerService.listAllGroomers());
        //model.addAttribute("groomer", new Groomer());
        return "allGroomers";
        
    }

    // @GetMapping("/manager/groomerList/add")
    // public String addGroomer(Model model){
    //     model.addAttribute("groomer", new Groomer());
    //     return "AddPet";
    // }

    @GetMapping("/manager/groomerList/add")
    public String addGroomer(Model model){
        model.addAttribute("groomer", new Groomer());
        return "AddPet";
    }
        
    

    @PostMapping("/manager/groomerList/add")
    public String addGroomer_M(@ModelAttribute("groomer") Groomer groomer){
        //System.out.println("Sucessful");
        groomerService.addGroomer_M(groomer);
        return "allGroomers";
    }



    

}
