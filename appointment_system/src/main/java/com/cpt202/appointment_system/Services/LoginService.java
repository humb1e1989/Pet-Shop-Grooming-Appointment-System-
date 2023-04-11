package com.cpt202.appointment_system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt202.appointment_system.Repositories.UserRepo;

// I think LoginService can be a part of Userservice
@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;

}
