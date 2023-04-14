package com.cpt202.appointment_system.Services;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.Pet;
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
	private GroomerRepo groomerRepo;

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
	public Result<?> getAppointmentDetail_M(@RequestParam Appointment appointment) {
		Appointment appointment1 = appointmentRepo.findByAid(appointment.getAid());
		if (appointment1 != null)
			return Result.success(appointment1, "Find Matching Appointment!");
		return Result.error("-1", "No Matching Appointment Found.");
	}



	/*
	 * Customer Part
	 */
	// YYY
	public Result<?> getAppointmentBy_Uid(@RequestParam User user) {
		// return appointmentRepo.findByUser(user);
		// return appointmentRepo.findByUsernameIs(user.getUsername());

		List<Appointment> appointmentList = userRepo.findByUidIs(user.getUid());
		if (!appointmentList.isEmpty()) {
			return Result.success(appointmentList, "Find Matching Customer!");
		}

		return Result.error("-1", "No Matching Customers Found.");
	}

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

	// ZYH : Customer can search appointment by user name
	public Result<?> getAppointmentListByUserName_C(@RequestParam String username) {
		List<Appointment> appointmentList = appointmentRepo.findByUserUsernameContaining(username);
		if (!appointmentList.isEmpty()) {
			return Result.success(appointmentList, "Find Matching Appointment!");
		}
		return Result.error("-1", "No Matching Appointment Found.");
	}


	// Bowen li's modification
	// Customer can make appointment when fill in all feilds
	public Result<?> makeAppointment_C(Appointment appointment) {
		Calendar calendar = Calendar.getInstance();
		Date currentdate = new Date(System.currentTimeMillis());
		appointment.setCreateTime(currentdate);
		appointment.setStatus("pending");
		Date temp =new Date(appointment.getStartTime().getTime());
        calendar.setTime(temp);
        Groomer OrderedGroomer=groomerRepo.findByGid(appointment.getGroomer().getGid());
        Pet pet=petRepo.findByPid(appointment.getPet().getPid());
        User user=userRepo.findByUid(appointment.getUser().getUid());
		List<Appointment> appointmentList=appointmentRepo.findByGroomer(OrderedGroomer);
        

        //if groomer or user or pet do not exists failed
		if(user==null){
			return Result.error("-2", "User is invalid");
		}
        if(OrderedGroomer==null){
			return Result.error("-2", "No such groomer");
		}
		if(pet==null){
			return Result.error("-2", "No such pet");
		}
	
        //different servicetype have different service time
		//the total price is depending on the rank of groomer, servicetype and pet_size
		if (appointment.getServiceType().equals("washing") ) {
            calendar.add(Calendar.MINUTE, 30);
			Date finishTime = new Date(calendar.getTimeInMillis());
			appointment.setFinishTime(finishTime);
            if(pet.getSize().equals("small")){
				appointment.setTotalprice(50 * (1 + 0.1 * OrderedGroomer.getRank())*0.5);
			}
			if(pet.getSize().equals("medium")){
				appointment.setTotalprice(50 * (1 + 0.1 * OrderedGroomer.getRank())*1.0);
			}
			if(pet.getSize().equals("large")){
				appointment.setTotalprice(50 * (1 + 0.1 * OrderedGroomer.getRank())*1.5);
			}
			
		}

		if (appointment.getServiceType().equals("haircut")) {
			calendar.add(Calendar.MINUTE, 40);
			Date finishTime = new Date(calendar.getTimeInMillis());
			appointment.setFinishTime(finishTime);
			if(pet.getSize().equals("small")){
				appointment.setTotalprice(60 * (1 + 0.1 * OrderedGroomer.getRank())*0.5);
			}
			if(pet.getSize().equals("medium")){
				appointment.setTotalprice(60* (1 + 0.1 * OrderedGroomer.getRank())*1.0);
			}
			if(pet.getSize().equals("large")){
				appointment.setTotalprice(60* (1 + 0.1 * OrderedGroomer.getRank())*1.5);
			}
		}

		if (appointment.getServiceType().equals("drying")) {
			calendar.add(Calendar.MINUTE, 10);
			Date finishTime = new Date(calendar.getTimeInMillis());
			appointment.setFinishTime(finishTime);
			if(pet.getSize().equals("small")){
				appointment.setTotalprice(40* (1 + 0.1 * OrderedGroomer.getRank())*0.5);
			}
			if(pet.getSize().equals("medium")){
				appointment.setTotalprice(40 * (1 + 0.1 * OrderedGroomer.getRank())*1.0);
			}
			if(pet.getSize().equals("large")){
				appointment.setTotalprice(40 * (1 + 0.1 * OrderedGroomer.getRank())*1.5);
			}
			appointment.setTotalprice(40 * (1 + 0.1 * OrderedGroomer.getRank()));
		}

		//if the groomer has already been booked, return error
        for(int i=0;i<appointmentList.size();i++){
           boolean overlap= isOverlap(appointmentList.get(i).getStartTime(), appointmentList.get(i).getFinishTime(), 
		   appointment.getStartTime(), appointment.getFinishTime());
           if(overlap){
			return Result.error("-2", "the groomer has already been booked during that time");
		   }
		}
        
		appointmentRepo.save(appointment);
		return Result.success();
        
	}

	public static boolean isOverlap(Date start1, Date end1, Date start2, Date end2) {
        if (start1.before(end2) && start2.before(end1)) {
            return true;
        } else {
            return false;
        }
    }



	// ZYH PBI NO.i : Customer can cancel appointment
	public Result<?> cancelAppointment_C(@RequestParam int aid) {
		Appointment appointment = appointmentRepo.findByAid(aid);
		if (appointment != null) {
			appointment.setStatus("Cancelled");
			// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// TODO : curremt time form to be modified
			Date date = new Date(System.currentTimeMillis());
			appointment.setCancelTime(date);
			return Result.success(appointment, "Appointment Cancelled!");
		}
		return Result.error("-1", "No Matching Appointment Found.");
	}

	// ZYH PBI NO.iii : Customer can modify appointment
	// same problem as editProfile_C()
	public Result<?> modifyAppointment_C(Appointment appointment) {
		Appointment appointment1 = appointmentRepo.findByAid(appointment.getAid());
		if (appointment1 != null) {
			appointment1.setServiceType(appointment.getServiceType());
			appointment1.setGroomer(appointment.getGroomer());
			// appointment1.setPetName(appointment.getPetName());
			appointment1.setStartTime(appointment.getStartTime());
			appointment1.setTotalprice(appointment.getTotalprice());
			// TODO : New one or modified one?
			appointmentRepo.save(appointment1);
			return Result.success();
		}
		return Result.error("-1", "No Matching Appointment Found.");
	}


	// TODO : Not necessary for now
	// // ZYH PBI NO.ii : Customer can filter appointment by time
	// public Result<?> getAppointmentListByTime_C(@RequestParam String time) {
	// List<Appointment> appointmentList =
	// appointmentRepo.findByTimeContaining(time);
	// if (!appointmentList.isEmpty()) {
	// return Result.success(appointmentList, "Find Matching Appointment!");
	// }
	// return Result.error("-1", "No Matching Appointment Found.");
	// }
}
