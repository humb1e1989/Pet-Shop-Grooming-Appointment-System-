package com.cpt202.appointment_system.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/home")
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
    
    @GetMapping() 
    public String getFirstFourGroomers_C(Model model){

        List<Groomer> gList = groomerService.listAllGroomers();
        // this logic is not good enough but easy to implement
        if (gList.size() < 4) {
            Groomer groomer = new Groomer();
            groomer.setImageURL("/assets/images/no-user.png");
            groomer.setName("No Groomer");
            groomer.setDescription("-------------------------");
            model.addAttribute("g1", groomer);
            model.addAttribute("g2", groomer);
            model.addAttribute("g3", groomer);
            model.addAttribute("g4", groomer);
            return "Home";
        }

        model.addAttribute("g1", gList.get(0));
        model.addAttribute("g2", gList.get(1));
        model.addAttribute("g3", gList.get(2));
        model.addAttribute("g4", gList.get(3));
        return "Home";

    }


    @GetMapping("/groomers") 
    public String getAllGroomers(){
        return "Groomers";
    }


    // @GetMapping("/view-groomer")
    // public Result<?> viewGroomer_C(@RequestParam Integer gid){
    //     return groomerService.viewOneGroomer(gid);
    // }


    
}
