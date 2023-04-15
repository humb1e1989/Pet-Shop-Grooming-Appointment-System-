package com.cpt202.appointment_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/appointment-system")
public class GroomerController {

    @Autowired
    private GroomerService groomerService;

    
    // // Manager Part
    // @GetMapping("/manager/grommerList")  
    // public Result<?> getAllGroomers_M(){
    //     return groomerService.listAllGroomers();
        
    // }

    @GetMapping("/manager/groomerList/view")
    public Result<?> viewGroomer_M(@RequestParam Integer gid){
        return groomerService.viewOneGroomer(gid);
    }

    @GetMapping("/manager/groomerList/search")
    public Result<?> searchGroomerById_M(@RequestParam Integer gid){
        return groomerService.searchGroomerByFullID_M(gid);
    }

    // @PostMapping("/manager/groomerList/add")
    // public Result<?> addGroomer_M(@RequestBody Groomer groomer){
    //     return groomerService.addGroomer_M(groomer);
    // }

    @PostMapping("/manager/groomerList/edit")
    public void editGrommer_M(@RequestBody Groomer groomer){
        groomerService.editGroomer_M(groomer);
    }


    // // Customer part
    // getAllGroomers_C is just for test, it'll be further modified.
    // @GetMapping("/") 
    // public Result<?> getAllGroomers_C(){
    //     return groomerService.listAllGroomers();
    // }

    // @GetMapping("/view-groomer")
    // public Result<?> viewGroomer_C(@RequestParam Integer gid){
    //     return groomerService.viewOneGroomer(gid);
    // }


    



    
}
