package com.cpt202.appointment_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
<<<<<<< HEAD
import com.cpt202.appointment_system.Models.UserTool;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.PetRepo;
=======
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
>>>>>>> a7d40deda916c94fd3cc9bb227f0b6eafde1f5c5
import com.cpt202.appointment_system.Repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private AppointmentRepo appointmentRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PetRepo petRepo;

    public Result<?> listAllCustomers_M(){
        byte typeCustomer = 0;
        List<User> userList = userRepo.findByType(typeCustomer);

        if (! userList.isEmpty()) {
            return Result.success(userList, "Successfully List All Customers!");
        }
    
        return Result.error("-1", "No Registered Customers.");
        
    }

    public Result<?> searchCustomerByName_M(User user){
        List<User> userList = userRepo.findByUsernameContaining(user.getUsername());

        if(! userList.isEmpty()){
            return Result.success(userList, "Find Matching Customers!");
        }

        return Result.error("-1","No Matching Customers Found.");
    }


    // I think it might have a better way to do it.
    // However, right now I do not know how to join many tables using Jpa. 
    public Result<?> viewOneCustomer_M(User u){
        User user = userRepo.findByUid(u.getUid());
        
        if (user != null) {
            List<Appointment> al = appointmentRepo.findByUser(user);
            List<Pet> pl = petRepo.findByUser(user);
            UserTool ut = new UserTool(user, al, pl);
            return Result.success(ut, "");
        }

        return Result.error("-1", "Customer Doesn't Exist");

    }
    
<<<<<<< HEAD
=======
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
>>>>>>> a7d40deda916c94fd3cc9bb227f0b6eafde1f5c5

}
