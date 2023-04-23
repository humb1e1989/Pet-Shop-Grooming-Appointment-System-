package com.cpt202.appointment_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.PetRepo;

@Service
public class PetService {

    @Autowired
    private PetRepo petRepo;

    //ZYH PBI NO.i Customer can add a new pet
    public Result<?> addPet_C(Pet pet) {
        petRepo.save(pet);
        return Result.success(pet, "Pet added succssfully!");
    }

    //ZYH PBI NO.i Customer can delete a pet from petlist
    public Result<?> deletePet_C(Integer pid) {
        petRepo.deleteById(pid);
        return Result.success(null, "Pet deleted succssfully!");
    }

    //Bowen Li Customer can get all pets
    public List<Pet> listAllPets(User user) {
        List<Pet> petList = petRepo.findByUser(user);
        return petList;
    }
}
