package com.cpt202.appointment_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Repositories.GroomerRepo;

@Service
public class GroomerService {

    @Autowired
    private GroomerRepo groomerRepo;

    // CYZ
    public List<Groomer> listAllGroomers() {
        List<Groomer> groomerList = groomerRepo.findAll();
        return groomerList;
    }

    // CYZ
    // later can improve:
    // groomer's info should also contain other things
    // (appointment records...)
    public Result<?> viewOneGroomer(Integer gid) {

        Groomer groomer = groomerRepo.findByGid(gid);

        if (groomer != null) {
            return Result.success(groomer, "Find All Info About This Groomer!");
        }

        return Result.error("-1", "Groomer Doesn't Exist.");

    }

    // CYZ
    public Result<?> searchGroomerByFullID_M(Integer gid) {

        Groomer groomer = groomerRepo.findByGid(gid);

        if (groomer != null) {
            return Result.success(groomer, "Find Matching Groomer!");
        }

        return Result.error("-1", "No Matching Groomer.");

    }

    // CYZ
    public void addGroomer_M(Groomer g) {

        Groomer groomer = groomerRepo.findByPhoneNumber(g.getPhoneNumber());

        if (groomer == null) {
            groomerRepo.save(g);
        }

    }

    // CYZ
    // it might have a more efficient way to implement dynamically update
    // save() will update every field of the specific record in the table, 
    // even if some fields are null, 
    // which means save() will simply override the all existing fields in the table 
    public Result<?> editGroomer_M(Groomer g){
        
        Groomer groomer = groomerRepo.findByGid(g.getGid());

        if (g.getName() == null) {
            g.setName(groomer.getName());
        }

        if (g.getGender() == null) {
            g.setGender(groomer.getGender());
        }

        if (g.getImageURL() == null) {
            g.setImageURL(groomer.getImageURL());
        }

        if (g.getRank() == null) {
            g.setRank(groomer.getRank());
        }

        if (g.getDescription() == null) {
            g.setDescription(groomer.getDescription());
        }

        if (g.getPhoneNumber() == null) {
            g.setPhoneNumber(groomer.getPhoneNumber());
        } else {
            if (groomerRepo.findByPhoneNumber(g.getPhoneNumber()) == null) {
                groomerRepo.save(g);
                return Result.success();
            }
            return Result.error("-2", "Phone Number Exists or Remains the Same.");
        }

        groomerRepo.save(g);
        return Result.success();

    }


    // YYY PBI NO.4 - Get a groomer list by groomer name, rank and phone number, no matter manager or customer
    public Result<?> searchGroomerByName(@RequestParam Groomer groomer) {

        List<Groomer> groomerList = groomerRepo.findByNameIs(groomer.getName());

		if (!groomerList.isEmpty()) {
			return Result.success(groomerList, "Find Matching Appointments!");
		}
		return Result.error("-1", "No Matching Appointment Found.");

    }

    public Result<?> searchGroomerByRank(@RequestParam Groomer groomer) {

        List<Groomer> groomerList = groomerRepo.findByRankIs(groomer.getRank());

		if (!groomerList.isEmpty()) {
			return Result.success(groomerList, "Find Matching Appointments!");
		}
		return Result.error("-1", "No Matching Appointment Found.");

    }

    public Result<?> searchGroomerByPhoneNumber(@RequestParam Groomer groomer) {

        List<Groomer> groomerList = groomerRepo.findByPhoneNumberIs(groomer.getPhoneNumber());

		if (!groomerList.isEmpty()) {
			return Result.success(groomerList, "Find Matching Appointments!");
		}
		return Result.error("-1", "No Matching Appointment Found.");

    }

}
