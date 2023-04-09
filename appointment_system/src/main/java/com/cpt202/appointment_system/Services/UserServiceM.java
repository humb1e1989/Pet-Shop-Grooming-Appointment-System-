package com.cpt202.appointment_system.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;

@Service
public class UserServiceM {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PetRepo petRepo;

    public Result<?> listAllCustomers(){
        byte typeCustomer = 0;
        List<User> userList = userRepo.findByType(typeCustomer);

        if (! userList.isEmpty()) {
            return Result.success(userList, "Successfully List All Customers!");
        }
    
        return Result.error("-1", "No Registered Customers.");
        
    }

    public Result<?> searchCustomerByName(User user){
        List<User> userList = userRepo.findByUsernameContaining(user.getUsername());

        if(! userList.isEmpty()){
            return Result.success(userList, "Find Matching Customers!");
        }

        return Result.error("-1","No Matching Customers Found.");
    }

    // Personal perspective (Yuzhen Chen): 
    // 
    public Result<?> viewOneCustomer(@RequestParam User user){
        User u = userRepo.findByUid(user.getUid());
        
        if (u != null) {
            List<Appointment> appointmentList = appointmentRepo.findByUser(user)  ;
            List<Pet> petList = petRepo.findByUser(user);
            u.setAppointmentList(appointmentList);
            u.setPetList(petList);
            return Result.success(u, "");
        }

        return Result.error("-1", "Customer Doesn't Exist");

    }
    

}
