package com.cpt202.appointment_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpt202.appointment_system.Common.Result;
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

	/*
	 * Manager Part
	 * This is a part to fullfill all the functions of managers.
	 */

	// WJT Manger Part
	// Fiter Fuction
	public List<Appointment> getAppointmentBy_CName(@RequestParam String username) {

		List<User> userList = userRepo.findByUsernameContaining(username);
		User findUser = userList.get(0);
		return appointmentRepo.findByUser(findUser);
		// userRepo.findByUsernameContaining(appointment.getUser().getUsername());

	}

	public List<Appointment> getAppointmentBy_Service(@RequestParam String servicetype) {
		return appointmentRepo.findByserviceType(servicetype);
	}

	public List<Appointment> getAppointmentBy_GroomerName(@RequestParam String grommername) {
		List<Groomer> groList = groRepo.findByNameContaining(grommername);
		Groomer findGroomer = groList.get(0);
		return appointmentRepo.findByGroomer(findGroomer);
	}

	// YYY
	// public Result<?> getAppointmentList_C(@RequestParam User user) {
	// 	// return appointmentRepo.findByUser(user);
	// 	// return appointmentRepo.findByUsernameIs(user.getUsername());

	// 	List<Appointment> appointmentList = appointmentRepo.findByUidIs(user.getUid());
	// 	if (!appointmentList.isEmpty()) {
	// 		return Result.success(appointmentList, "Find Matching Customer!");
	// 	}

	// 	return Result.error("-1", "No Matching Customers Found.");
	// }

	// YYY (modified by ZYH)
	public Result<?> getAppointmentDetail_C(@RequestParam User user) {
		Appointment appointment1 = appointmentRepo.findByAid(user.getUid());
		if (appointment1 != null)
			return Result.success(appointment1, "Find Matching Appointment!");
		return Result.error("-1", "No Matching Appointment Found.");
	}

	/* ZYH */
	// TODO : para name to be uniformed
	// YYY - Manager can view all appointments
	public Result<?> getAppointmentList_M() {
		List<Appointment> appointmentList = appointmentRepo.findAll();
		if (!appointmentList.isEmpty()) {
			return Result.success(appointmentList, "Find Matching Appointments!");
		}
		return Result.error("-1", "No Matching Appointment Found.");
	}

	// Manager can view all appointments' details

	// public Result<?> getAppointmentDetail_M(@RequestParam User user) {
	// 	Appointment appointment1 = appointmentRepo.findByAid(user.getUid());
	// 	if (appointment1 != null) {
	// 		return Result.success(appointment1, "Find Matching Appointment!");
	// 	}
	// 	return Result.error("-1", "No Matching Appointment Found.");
	// }

	// Customer can view only his appointments' details
	// public Result<?> getAppointmentDetail_C(@RequestParam int aid, User user) {
	// Appointment appointment1 = appointmentRepo.findByAid(aid);
	// if(appointment1 != null) return Result.success(appointment1, "Find Matching
	// Appointment!");
	// return Result.error("-1", "No Matching Appointment Found.");
	// }
     
	//Bowen li's modification
	// Customer can make appointment when fill in all feilds 
    public Result<?>makeAppointment_C(Appointment appointment) {
        
        

		if(appointment .getServiceType()=="washing"){
		  appointment.setTotalprice(50*(1+0.1*appointment.getGroomer().getRank()));
		}
  
		if(appointment.getServiceType()=="haircut"){
		  appointment.setTotalprice(60*(1+0.1*appointment.getGroomer().getRank()));
		}
		if(appointment.getServiceType()=="drying"){
		  appointment.setTotalprice(40*(1+0.1*appointment.getGroomer().getRank()));
		}

		
		appointmentRepo.save(appointment);
		return Result.success();
        
	}

}
