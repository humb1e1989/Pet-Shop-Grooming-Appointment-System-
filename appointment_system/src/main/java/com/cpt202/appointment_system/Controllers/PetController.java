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
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Services.PetService;
import com.cpt202.appointment_system.Services.UserService;

@Controller
@RequestMapping("/appointment-system")
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    // ZYH PBI NO.ii Customer can view all his pets
    @GetMapping("/customer/petList")
    public Result<?> getAllPets(@RequestParam Integer uid){
        return userService.listAllPets_C(uid);
    }

    //ZYH PBI NO.i Customer can add a new pet
    @PostMapping("/customer/petList/add")
    public Result<?> addPet(@RequestBody Pet pet){
        return petService.addPet_C(pet);
    }

    //ZYH PBI NO.i Customer can delete a pet from petlist
    @PostMapping("/customer/petList/delete")
    public Result<?> deletePet(@RequestParam Integer pid){
        return petService.deletePet_C(pid);
    }

    //SQZ And WJT
    @PostMapping("/customer/petList/update")
    public Result<?> deletePet(@RequestBody Pet pet){
        return petService.updatePet_C(pet);
    }

}