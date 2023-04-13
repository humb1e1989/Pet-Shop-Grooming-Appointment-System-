package com.cpt202.appointment_system.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Services.PetService;
import com.cpt202.appointment_system.Services.UserService;


@RestController // a RESTfull API
@RequestMapping("/appointment-system")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    /* Manager Part
        This is a part to fullfill all the functions of manager.
    */ 

    // Manager can view all of customers
    @GetMapping("/manager/customerList")  
    public Result<?> getAllCustomers(){
        return userService.listAllCustomers_M();
    }

    // No.i: Manager can search customer by name
    @GetMapping("/manager/customerList/search")
    public Result<?> getCustomerByName(@RequestParam String username){
        return userService.searchCustomerByName_M(username);
    }

    // Manager can view customer detail
    @GetMapping("/manager/customerList/view")
    public Result<?> viewCustomer(@RequestParam Integer uid){
        return userService.viewOneCustomer_M(uid);
    }

    /* Customer Part
        This is a part to fullfill all the functions of customer.
     */

     //TODO : Number
     // ZYH PBI NO.i Customer can search a groomer by name
    @GetMapping("/customer/groomerList/search")
    public Result<?> getGroomerByName(@RequestParam String username){
        return userService.searchGroomerByName_C(username);
    }
}
