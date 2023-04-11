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

<<<<<<< HEAD
  // WJT Manger Part
  // Fiter Fuction
  public List<User> getAppointmentBy_CName(@RequestParam Appointment appointment) {
    return userRepo.findByUsernameContaining(appointment.getUser().getUsername());
  }

  public List<Appointment> getAppointmentBy_Service(@RequestParam Appointment appointment) {
    return appointmentRepo.findByserviceType(appointment.getServiceType());
  }

  public List<Groomer> getAppointmentBy_GrommerName(@RequestParam Appointment appointment) {
    return groRepo.findByNameContaining(appointment.getGroomer().getName());
  }
=======
    //WJT Manger Part
    //Fiter Fuction
    public List<Appointment> getAppointmentBy_CName(@RequestParam Appointment appointment)
		{
			
      List<User> userList = userRepo.findByUsernameContaining(appointment.getUser().getUsername());
      User findUser = userList.get(0);
      return appointmentRepo.findByUser(findUser);
      // userRepo.findByUsernameContaining(appointment.getUser().getUsername());

		}

    public List<Appointment> getAppointmentBy_Service(@RequestParam Appointment appointment)
		{
			return appointmentRepo.findByserviceType(appointment.getServiceType());
		}

    public List<Appointment> getAppointmentBy_GrommerName(@RequestParam Appointment appointment)
		{
			List<Groomer> groList= groRepo.findByNameContaining(appointment.getGroomer().getName());
      Groomer findGroomer = groList.get(0);
      return appointmentRepo.findByGroomer(findGroomer);
		}
>>>>>>> 4075a43fe6f4af14809fb9aee068d24e16249360

  // WJT Fiter Time
}
