package com.cpt202.appointment_system.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Services.PetService;
import com.cpt202.appointment_system.Services.UserService;

@Controller
@RequestMapping("/customer")
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;


    //SQZ And WJT
    // @PostMapping("/customer/petList/update")
    // public Result<?> deletePet(@RequestBody Pet pet){
    //     return petService.updatePet_C(pet);
    // }

    // User can choose from his own pet list
    @GetMapping("/makeAppointment")
    public String getPetList(Model model, int uid){
        User user = userRepo.findByUid(uid);
        List<Pet> petList = petRepo.findByUser(user);
        model.addAttribute("petList", petList);
        return "makeAppointment";
    }





    
    // below are all the things needed by personal page

    // list, add, modify, delete on one page
    // remember to optimize "return problem"
    @GetMapping("/profile/pet")
    public String getMyPetPage(Model model, HttpSession session){
        String username = (String) session.getAttribute("user");
        User user = userRepo.findByUsername(username);

        List<Pet> petList = petService.listAllPets(user);
        List<Pet> petShown = new ArrayList<>();
        for (Pet pet: petList){
            if (pet.getSize().equals("") && pet.getType().equals("")){
            }
            else {
                petShown.add(pet);
            }
        }

        model.addAttribute("petList", petShown);
        model.addAttribute("number", petList.size());
        model.addAttribute("pet", new Pet());
        return "MyPet8";
    }



    @PostMapping("/profile/pet/add")
    public String addPetPost(HttpSession session, @ModelAttribute("pet") Pet pet, Model model, MultipartFile file){
        String username = (String) session.getAttribute("user");
        User user = userRepo.findByUsername(username);
        Integer uid=user.getUid();
        pet.setUser(userRepo.findByUid(uid));
        int code = petService.addPet(file, pet);
        model.addAttribute("code", code);

        // have to deal with it later
        if (code == 2 || code == 3){
            return "MyPet8";
        }

        return "redirect:/customer/profile";
        
    }

    @PostMapping("/profile/pet/add2")
    public String addPetPost2(HttpSession session, @ModelAttribute("pet") Pet pet, Model model, MultipartFile file){
        String username = (String) session.getAttribute("user");
        User user = userRepo.findByUsername(username);
        Integer uid=user.getUid();
        pet.setUser(userRepo.findByUid(uid));
        int code = petService.addPet(file, pet);
        model.addAttribute("code", code);

        // have to deal with it later
        if (code == 2 || code == 3){
            return "MyPet8";
        }

        return "redirect:/Appointment/appoint";
        
    }


    @PostMapping("/profile/pet/edit")
    public String editPetPost(HttpSession session, @ModelAttribute("pet") Pet pet, Model model, MultipartFile file){

        String username = (String) session.getAttribute("user");
        User user = userRepo.findByUsername(username);
        Integer uid=user.getUid();
        pet.setUser(userRepo.findByUid(uid));

        int code = petService.editPet(file, pet);
        model.addAttribute("code", code);

        // have to deal with it later
        if (code == 2 || code == 3){
            return "MyPet8";
        }

        return "redirect:/customer/profile";
    }


    @PostMapping("/profile/pet/delete")
    public String removePet(HttpSession session, @ModelAttribute("pet") Pet p, Model model){
        String username = (String) session.getAttribute("user");
        User user = userRepo.findByUsername(username);

        Pet pet = petRepo.findByPid(p.getPid());
        petService.pseudoDeletePet(pet, user);

        return "redirect:/customer/profile";
                
    }



}