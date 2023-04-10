package com.cpt202.appointment_system.Controllers;

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

@RestController
@RequestMapping("/appointment-system")
public class GroomerController {

    @Autowired
    private GroomerService groomerService;

    // Manager Part
    @GetMapping("/manager/grommerList")  
    public Result<?> getAllGroomers_M(){
        return groomerService.listAllGroomers();
    }

    @GetMapping("manager/groomerList/view")
    public Result<?> viewGroomer_M(@RequestParam Groomer groomer){
        return groomerService.viewOneGroomer(groomer);
    }

    @GetMapping("manager/groomerList/search")
    public Result<?> searchGroomer_M(@RequestParam Groomer groomer){
        return groomerService.searchGroomer(groomer);
    }

    @PostMapping("manager/groomerList/add")
    public void addGroomer_M(@RequestBody Groomer groomer){
        groomerService.addGroomer(groomer);
    }

    @PostMapping("manager/groomerList/edit")
    public void editGrommer_M(@RequestBody Groomer groomer){
        groomerService.editGroomer(groomer);
    }

    



    
}
