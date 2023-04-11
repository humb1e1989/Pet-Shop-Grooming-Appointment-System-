package com.cpt202.appointment_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Repositories.GroomerRepo;

@Service
public class GroomerService {

    @Autowired
    private GroomerRepo groomerRepo;

    //CYZ
    public Result<?> listAllGroomers(){

        List<Groomer> groomerList = groomerRepo.findAll();

        if (!groomerList.isEmpty()) {
            return Result.success(groomerList, "Successfully List All Grommers!");
        }
    
        return Result.error("-1", "No Groomers.");
        
    }


    //CYZ
    // later can improve:
    // groomer's info should also contain other things
    // (appointment records...)
    public Result<?> viewOneGroomer(int gid){

        Groomer groomer = groomerRepo.findByGid(gid);

        if (groomer != null) {
            return Result.success(groomer, "Find All Info About This Groomer!");
        }

        return Result.error("-1", "Groomer Doesn't Exist.");

    }


    //CYZ
    public Result<?> searchGroomerByFullID(int gid){

        Groomer groomer = groomerRepo.findByGid(gid);

        if (groomer != null) {
            return Result.success(groomer, "Find Matching Groomer!");
        }

        return Result.error("-1", "No Matching Groomer.");

    }


    // CYZ
    public Result<?> addGroomer(Groomer g){

        Groomer groomer = groomerRepo.findByPhoneNumber(g.getPhoneNumber());

        if (groomer == null){
            groomerRepo.save(g);
            return Result.success();
        }

        return Result.error("-2", "Phone Number Exists.");
        
    }


    // CYZ
    // later must improve:
    // 1. certain fields cannot be duplicate (no same phoneNumber...)
    // 2. dynamically update fields 
    public void editGroomer(Groomer g){
        
        // when save() is used to update, the PK of updated object must be set already
        // otherwise, save() will be used as "add"
        groomerRepo.save(g);

    }



}
