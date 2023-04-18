package com.cpt202.appointment_system.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    
    // Customer part
    @GetMapping() 
    public String getRandomFourGroomers_C(Model model){

        List<Groomer> gList = groomerService.listAllGroomers();

        if (gList.size() < 4) {

            Groomer groomer = new Groomer();
            groomer.setImageURL("/assets/images/no-user.png");
            groomer.setName("No Groomer");
            groomer.setDescription("No Description");
            if (gList.size() == 0){
                for (int i = 0; i < 4; ++i) {
                    String attribName = "g" + i;
                    model.addAttribute(attribName, groomer);
                }
                return "home";
            }

            int temp = 0;
            for (int i = 0; i < gList.size(); ++i) {
                String attribName = "g" + i;
                model.addAttribute(attribName, gList.get(i));
                temp = i;
            }
             
            for (int i = temp + 1; i < 4; ++i) {
                String attribName = "g" + i;
                model.addAttribute(attribName, groomer);
            }

            return "home";
        }
        
        // ensure the random num not duplicate

        Random r = new Random();
        List<Integer> intList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int ran = r.nextInt(gList.size());
            while (intList.contains(ran)){
                ran = r.nextInt(gList.size());
            }
            intList.add(ran);
            String attribName = "g" + i;
            model.addAttribute(attribName, gList.get(ran));
        }
        
        return "home";

    }


    @GetMapping("/groomers") 
    public String getAllGroomers(){
        return "Groomers";
    }


    // This method may never be used
    // @GetMapping("/view-groomer")
    // public Result<?> viewGroomer_C(@RequestParam Integer gid){
    //     return groomerService.viewOneGroomer(gid);
    // }


}
