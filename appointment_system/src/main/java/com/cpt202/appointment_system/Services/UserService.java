package com.cpt202.appointment_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Models.UserTool;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PetRepo petRepo;

    // CYZ
    public Result<?> listAllCustomers_M() {
        byte typeCustomer = 0;
        List<User> userList = userRepo.findByType(typeCustomer);

        if (!userList.isEmpty()) {
            return Result.success(userList, "Successfully List All Customers!");
        }

        return Result.error("-1", "No Registered Customers.");

    }

    // CYZ
    // I think it might have a better way to do it.
    // However, right now I do not know how to join many tables using Jpa. 
    public Result<?> viewOneCustomer_M(int uid){
        User user = userRepo.findByUid(uid);
        
        if (user != null) {
            List<Appointment> al = appointmentRepo.findByUser(user);
            List<Pet> pl = petRepo.findByUser(user);
            UserTool ut = new UserTool(user, al, pl);
            return Result.success(ut, "Find All Info About This Customer!");
        }

        return Result.error("-1", "Customer Doesn't Exist.");

    }

    
    //CYZ
    public Result<?> searchCustomerByName_M(String username){
        List<User> userList = userRepo.findByUsernameContaining(username);

        if (!userList.isEmpty()) {
            return Result.success(userList, "Find Matching Customers!");
        }

        return Result.error("-1", "No Matching Customers Found.");
    }

    // // just a test demo
    // // YYY
    // public List<Appointment> getAppointmentList_M() {
    //     return appointmentRepo.findAll();
    // }

    // YYY
    public List<Appointment> getAppointmentList_C(@RequestParam User user) {
        return appointmentRepo.findByUser(user);
        // return appointmentRepo.findByUsernameIs(user.getUsername());

        // List<Appointment> appointmentList =
        // userRepo.findByFirstnameIs(user.getUsername());
        // if(! appointmentList.isEmpty()){
        // return Result.success(appointmentList, "Find Matching Customer!");
        // }

        // return Result.error("-1","No Matching Customers Found.");
    }

    // YYY (modified by ZYH)
    public Result<?> getAppointmentDetail_C(int aid) {
        Appointment appointment1 =  appointmentRepo.findByAid(aid);
        if(appointment1 != null) return Result.success(appointment1, "Find Matching Appointment!");
        return Result.error("-1", "No Matching Appointment Found.");
    }

    /* ZYH */
    // TODO : para name to be uniformed
    // Manager can view all appointments
    public Result<?> getAppointmentList_M() {
        List<Appointment> appointmentList = appointmentRepo.findAll();
        if (!appointmentList.isEmpty()) {
            return Result.success(appointmentList, "Find Matching Appointments!");
        }
        return Result.error("-1", "No Matching Appointment Found.");
    }

    // Manager can view all appointments' details
    public Result<?> getAppointmentDetail_M(@RequestParam int aid) {
        Appointment appointment1 = appointmentRepo.findByAid(aid);
        if (appointment1 != null) {
            return Result.success(appointment1, "Find Matching Appointment!");
        }
        return Result.error("-1", "No Matching Appointment Found.");
    }

    // // Customer can view only his appointments' details
    // public Result<?> getAppointmentDetail_C(@RequestParam int aid, User user) {
    //     Appointment appointment1 = appointmentRepo.findByAid(aid);
    //     if(appointment1 != null) return Result.success(appointment1, "Find Matching Appointment!");
    //     return Result.error("-1", "No Matching Appointment Found.");
    // }
}

