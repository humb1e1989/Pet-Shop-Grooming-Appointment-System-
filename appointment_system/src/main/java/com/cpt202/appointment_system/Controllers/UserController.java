package com.cpt202.appointment_system.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

// @Controller
@RestController
@RequestMapping("/appointment-system")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    /*
     * Manager Part
     * This is a part to fullfill all the functions of manager.
     */

    // Manager can view all of customers
    @GetMapping("/manager/customerList")
    public String ViewCustomerList_M(Model model) {
        List<User> users = userService.listAllCustomers_M();
        model.addAttribute("users", users);
        return "manager/customerList";
    }

    // No.i: Manager can search customer by name
    @GetMapping("/manager/customerList/search")
    public Result<?> getCustomerByName(@RequestParam String username) {
        return userService.searchCustomerByName_M(username);
    }

    // Manager can view customer detail
    @GetMapping("/manager/customerList/view")
    public Result<?> viewCustomer(@RequestParam Integer uid) {
        return userService.viewOneCustomer_M(uid);
    }

    // // Manager can delete customer by id
    // @PostMapping("/manager/customerList/delete")
    // public String deleteUser(@RequestParam("uid") Integer uid) {
    // userService.deleteUserById(uid);
    // return "redirect:/appointment-system/manager/customerList";
    // }

    

    // @GetMapping("/manager")
    // public String manager() {
    //     return "manager";
    // }

    // @PostMapping("/manager")
    // public String manager(@RequestParam("username") String username, @RequestParam("password") String password,
    //         Model model) {
    //     if (username.equals("manager") && password.equals("123456")) {
    //         return "redirect:/appointment-system/manager/customerList";
    //     } else {
    //         model.addAttribute("msg", "Wrong username or password!");
    //         return "manager";
    //     }
    // }

    /*
     * Customer Part
     * This is a part to fullfill all the functions of customer.
     */

    // TODO : Number
    // ZYH PBI NO.i Customer can search a groomer by name
    @GetMapping("/customer/groomerList/search")
    public Result<?> getGroomerByName(@RequestParam String username) {
        return userService.searchGroomerByName_C(username);
    }

    // ZYH PBI NO.ii Customer can view his/her profile
    @GetMapping("/customer/profile")
    public Result<?> viewProfile(@RequestParam Integer uid) {
        return userService.viewProfile_C(uid);
    }

    // ZYH PBI NO.iii Customer can edit his/her profile
    @PostMapping("/customer/profile/edit")
    public Result<?> editProfile(@RequestBody User user) {
        return userService.editProfile_C(user);
    }

}
