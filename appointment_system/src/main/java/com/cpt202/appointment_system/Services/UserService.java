package com.cpt202.appointment_system.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private AppointmentRepo appointmentRepo;

    public Result<?> listAllCustomers_m(){
        byte typeCustomer = 0;
        List<User> userList = userRepo.findByType(typeCustomer);
        if (! userList.isEmpty()) {
            return Result.success(userList, "Successfully List All Customers!");
        }
    
        return Result.error("-1", "No Registered Customers.");
        
    }

    public Result<?> searchCustomerByName_m(@RequestParam User user){
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


    public List<Appointment> getAppointmentList_m(){
        return appointmentRepo.findAll();
    }

    public List<Appointment> getAppointmentList_c(@RequestParam User user){
        return appointmentRepo.findByFirstnameIs(user.getUsername());

        // List<Appointment> appointmentList = userRepo.findByFirstnameIs(user.getUsername());
        // if(! appointmentList.isEmpty()){
        //     return Result.success(appointmentList, "Find Matching Customer!");
        // }

        // return Result.error("-1","No Matching Customers Found.");
    }

}
