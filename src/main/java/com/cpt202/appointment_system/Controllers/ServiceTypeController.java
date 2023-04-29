package com.cpt202.appointment_system.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.ServiceType;
import com.cpt202.appointment_system.Services.ServiceTypeService;




@Controller
@RequestMapping("/appointment-system")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @GetMapping("/allService")
    public String getAllServicePage(Model model){
        return "AllService";
    }

    @GetMapping("/petBathing")
    public String getPetBathingPage(Model model){
        return "PetBathing";
    }

    @GetMapping("/petDyeHair")
    public String getPetDyeHairPage(Model model){
        return "PetDyeHair";
    }

    @GetMapping("/petHaircut")
    public String getPetHaircutPage(Model model){
        return "PetHaircut";
    }

    @GetMapping("/petTraining")
    public String getPetTrainingPage(Model model){
        return "PetTraining";
    }

    @GetMapping("/veterinaryCare")
    public String getVeterinaryCarePage(Model model){
        return "VeterinaryCare";
    }

    @GetMapping("/hairDrying")
    public String getHairDryingPage(Model model){
        return "HairDrying";
    }


    @GetMapping("/pricing")
    public String getPricingPage(Model model){
        List<ServiceType> serviceTypeList = serviceTypeService.getAllServiceTypes();
        model.addAttribute("stList", serviceTypeList);
        return "Pricing";
    }
    

    //Manager can add a new service to the database
    @PostMapping("/addService")
    public Result<?> addService(@RequestBody ServiceType service){
        return serviceTypeService.addService_M(service);
    }

    //Customer can view all the service
    // @PostMapping("/manager/addService")
    // public Result<?> listService(@@RequestParam ServiceType service){
    //     return serviceTypeService.addService_M(service);
    // }

}
