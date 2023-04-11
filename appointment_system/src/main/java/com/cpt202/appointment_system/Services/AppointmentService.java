package com.cpt202.appointment_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.GroomerRepo;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;

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
    public List<Appointment> getAppointmentBy_CName(@RequestParam String username)
		{
			
      List<User> userList = userRepo.findByUsernameContaining(username);
      User findUser = userList.get(0);
      return appointmentRepo.findByUser(findUser);
      // userRepo.findByUsernameContaining(appointment.getUser().getUsername());

		}

    public List<Appointment> getAppointmentBy_Service(@RequestParam String servicetype)
		{
			return appointmentRepo.findByserviceType(servicetype);
		}

    public List<Appointment> getAppointmentBy_GrommerName(@RequestParam String grommername)
		{
			List<Groomer> groList= groRepo.findByNameContaining(grommername);
      Groomer findGroomer = groList.get(0);
      return appointmentRepo.findByGroomer(findGroomer);
		}
  // WJT Fiter Time
}
