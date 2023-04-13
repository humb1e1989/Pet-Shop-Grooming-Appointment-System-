package com.cpt202.appointment_system.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import java.util.Optional;

@Controller
@RequestMapping("/appointment-system")
public class LoginController {

    // @Autowired
    // private LoginService loginService;

    // @PostMapping("/register")
    // public ResponseEntity register(User user){
    //     return loginService.registerUser(user);
    // }

    // @GetMapping("/login")  
    // public ResponseEntity login(User user, HttpSession session ){
    //     return loginService.loginUser(user,session);
    // }

    // @GetMapping("/logout")  
    // public ResponseEntity logout(User user, HttpSession session){
    //     return loginService.logoutUser(session);
    // }



}
