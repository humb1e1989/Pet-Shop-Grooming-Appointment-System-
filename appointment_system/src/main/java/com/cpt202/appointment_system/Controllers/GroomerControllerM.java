package com.cpt202.appointment_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cpt202.appointment_system.Services.GroomerServiceM;

@RestController
public class GroomerControllerM {

    @Autowired
    private GroomerServiceM groomerService;
    
}
