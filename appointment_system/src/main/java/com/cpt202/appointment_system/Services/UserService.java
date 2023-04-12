package com.cpt202.appointment_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.AccessOptions.GetOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Common.UserTool;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.GroomerRepo;
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

    @Autowired
    private GroomerRepo groomerRepo;

    // CYZ
    public Result<?> listAllCustomers_M() {
        Integer typeCustomer = 0;
        List<User> userList = userRepo.findByType(typeCustomer);

        if (!userList.isEmpty()) {
            return Result.success(userList, "Successfully List All Customers!");
        }

        return Result.error("-1", "No Registered Customers.");

    }

    // CYZ
    // I think it might have a better way to do it.
    // However, right now I do not know how to join many tables using Jpa.
    public Result<?> viewOneCustomer_M(Integer uid) {
        User user = userRepo.findByUid(uid);

        if (user != null) {
            List<Appointment> al = appointmentRepo.findByUser(user);
            List<Pet> pl = petRepo.findByUser(user);
            UserTool ut = new UserTool(user, al, pl);
            return Result.success(ut, "Find All Info About This Customer!");
        }

        return Result.error("-1", "Customer Doesn't Exist.");

    }

    // CYZ
    public Result<?> searchCustomerByName_M(String username) {
        List<User> userList = userRepo.findByUsernameContaining(username);

        if (!userList.isEmpty()) {
            return Result.success(userList, "Find Matching Customers!");
        }

        return Result.error("-1", "No Matching Customers Found.");
    }

    //TODO: Number
    // ZYH PBI NO.i Customer can view only his appointments' details
    public Result<?> getAppointmentDetail_C(@RequestParam Appointment appointment, User user) {
        List<Appointment> appointment1 = appointmentRepo.findByUser(user);
        if(appointment1 != null) return Result.success(appointment1, "Find Matching Appointment!");
        return Result.error("-1", "No Matching Appointment Found.");
    }

    //TODO : Number 
    // ZYH PBI NO.ii Customer can search a groomer by name
    public Result<?> searchGroomerByName_C(String name) {
        List<Groomer> groomerList = groomerRepo.findByNameContaining(name);
        if(!groomerList.isEmpty()) return Result.success(groomerList, "Find Matching Groomer!");
        return Result.error("-1", "No Matching Groomer Found.");
    }
}
