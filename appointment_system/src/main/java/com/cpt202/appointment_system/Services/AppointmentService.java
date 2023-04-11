package com.cpt202.appointment_system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt202.appointment_system.Repositories.AppointmentRepo;

@Service
public class AppointmentService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private GroomerRepo groRepo;

    //WJT Manger Part
    //Fiter Fuction
    public Appointment getAppointmentBy_CName(@RequestParam Appointment appointment)
		{
			return userRepo.findByUsernameContaining(appointment.getUser().getUsername());
		}

    public Appointment getAppointmentBy_Service(@RequestParam Appointment appointment)
		{
			return appointmentRepo.findByserviceType(appointment.getServiceType());
		}

    public Appointment getAppointmentBy_GrommerName(@RequestParam Appointment appointment)
		{
			return groRepo.findByNameContaining(appointment.getGroomer().getName());
		}

    //WJT Fiter Time
}
