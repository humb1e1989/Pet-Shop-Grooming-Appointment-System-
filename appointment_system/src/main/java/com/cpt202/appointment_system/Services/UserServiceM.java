package com.cpt202.appointment_system.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.UserRepo;

@Service
public class UserServiceM {

    @Autowired
    private UserRepo userRepo;

    public Result<?> listAllCustomers(){
        byte typeCustomer = 0;
        List<User> userList = userRepo.findByType(typeCustomer);
        if (! userList.isEmpty()) {
            return Result.success(userList, "Successfully List All Customers!");
        }
    
        return Result.error("-1", "No Registered Customers.");
        
    }

    public Result<?> searchCustomerByName(@RequestParam User user){
        List<User> userList = userRepo.findByUsernameContaining(user.getUsername());
        if(! userList.isEmpty()){
            return Result.success(userList, "Find Matching Customer!");
        }

        return Result.error("-1","No Matching Customers Found.");
    }


    // public Result<?> viewOneCustomer(@RequestParam User user){
    //     User u = userRepo.findByUid(user.getUid());
    //     if (u != null) {
    //         Appointment appointment =    ;
    //         Pet pet =    ;
    //         List<?> list = new ArrayList<>();
    //         list.add(u);
    //         list.add(appointment);
    //         list.add(pet);
    //         return Result.success(list, "");
    //     }

    //     return Result.error("-1", "Customer doesn't exist");

    // }
    
    // just a test demo
    public Result<?> listAllAppointment(){
        byte typeCustomer = 0;
        List<User> appointmentList = userRepo.findByType(typeCustomer);
        if (! appointmentList.isEmpty()) {
            return Result.success(appointmentList, "Successfully List All Customers!");
        }
    
        return Result.error("-1", "No Registered Customers.");
        
    }

}
