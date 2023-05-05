package com.cpt202.appointment_system.Services;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.ServiceType;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.GroomerRepo;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Repositories.ServiceTypeRepo;
import java.math.BigInteger;

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

	@Autowired
	private ServiceTypeRepo serviceTypeRepo;

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
	public Appointment getAppointmentBy_Aid(@RequestParam int aid) {
		Appointment appointmentDetail = appointmentRepo.findByAid(aid);

		return appointmentDetail;
	}

	/*
	 * Customer Part
	 */
	// YYY
	public List<Appointment> appointmentSearch(@RequestParam String keyword) {
		List<Appointment> resulList = new ArrayList<>();
		if (isInteger(keyword)) {
			Appointment resulList_aid = appointmentRepo.findByAid(Integer.valueOf(keyword).intValue());
			List<Appointment> resulList_gid = appointmentRepo.findByGroomer(Integer.valueOf(keyword).intValue());
			List<Appointment> resulList_sid = appointmentRepo.findBySid(Integer.valueOf(keyword).intValue());
			List<Appointment> resulList_price = appointmentRepo.findBytotalPrice(Double.valueOf(keyword));

			if (resulList_aid != null)
				resulList.add(resulList_aid);
			if (resulList_gid != null)
				resulList.addAll(resulList_gid);
			if (resulList_sid != null)
				resulList.addAll(resulList_sid);
			if (resulList_price != null)
				resulList.addAll(resulList_price);
		}
		// List<Appointment> resulList_gname = appointmentRepo.findByGname(keyword);
		// List<Appointment> resulList_servicetype = appointmentRepo.findByService_type(keyword);
		// List<Appointment> resulList_status = appointmentRepo.findByStatus(keyword);
		// if (resulList_gname != null)
		// resulList.addAll(resulList_gname);
		// if (resulList_servicetype != null)
		// 	resulList.addAll(resulList_servicetype);
		// if (resulList_status != null)
		// 	resulList.addAll(resulList_status);

		LinkedHashSet<Appointment> hashSet = new LinkedHashSet<>(resulList);
		ArrayList<Appointment> resulList_Final = new ArrayList<>(hashSet);
		return resulList_Final;
	}

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
	public List<Appointment> listAllAppointments() {
		List<Appointment> appointmentList = appointmentRepo.findAll();
		return appointmentList;
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
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		appointment.setCreateTime(currentTime);
		appointment.setStatus("Pending");
		Timestamp temp = new Timestamp(appointment.getStartTime().getTime());
		calendar.setTime(temp);
		Groomer OrderedGroomer = groomerRepo.findByGid(appointment.getGroomer().getGid());
		Pet pet = petRepo.findByPid(appointment.getPet().getPid());
		User user = userRepo.findByUid(appointment.getUser().getUid());
		List<Appointment> appointmentList = appointmentRepo.findByGroomer(OrderedGroomer);
		ServiceType servicetype = serviceTypeRepo.findBySid(appointment.getServiceType().getSid());

		// if groomer or user or pet do not exists failed
		if (user == null) {
			return Result.error("-2", "User is invalid");
		}
		if (OrderedGroomer == null) {
			return Result.error("-2", "No such groomer");
		}
		if (pet == null) {
			return Result.error("-2", "No such pet");
		}
		if (servicetype == null) {
			return Result.error("-2", "No such service");
		}

		// different servicetype have different service time
		// the total price is depending on the rank of groomer, servicetype and pet_size

		calendar.add(Calendar.MINUTE, servicetype.getServiceTime());
		Timestamp finishTime = new Timestamp(calendar.getTimeInMillis());
		appointment.setFinishTime(finishTime);
		
		if (pet.getSize().equals("Small")) {
			appointment.setTotalprice(10.0+OrderedGroomer.getRank()*10+servicetype.getBasicPrice());
		}
		if (pet.getSize().equals("Medium")) {
			appointment.setTotalprice(20.0+OrderedGroomer.getRank()*10+servicetype.getBasicPrice());
		}
		if (pet.getSize().equals("Large")) {
			appointment.setTotalprice(30.0+OrderedGroomer.getRank()*10+servicetype.getBasicPrice());
		}

		// if the groomer has already been booked, return error
		for (int i = 0; i < appointmentList.size(); i++) {
			boolean overlap = isOverlap(appointmentList.get(i).getStartTime(), appointmentList.get(i).getFinishTime(),
					appointment.getStartTime(), appointment.getFinishTime());
			if (overlap) {
				return Result.error("-2", "the groomer has already been booked during that time");
			}
		}

		appointmentRepo.save(appointment);
		return Result.success();

	}

	public static boolean isOverlap(Timestamp start1, Timestamp end1, Timestamp start2, Timestamp end2) {
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
			// Date date = new Date(System.currentTimeMillis());
			// appointment.setCancelTime(date);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			appointment.setCancelTime(currentTime);
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
			appointment1.setPet(appointment.getPet());
			appointment1.setStartTime(appointment.getStartTime());
			appointment1.setTotalprice(appointment.getTotalprice());
			appointment1.setFinishTime(appointment.getFinishTime());
			appointment1.setCreateTime(appointment.getCreateTime());
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

	public List<Appointment> searchAppointmentByUsername(String name) {
		return appointmentRepo.findByUserContaining(name);
	}

	public Result<?> getAppointmentDetail_M(@RequestParam Appointment appointment) {
		Appointment appointment1 = appointmentRepo.findByAid(appointment.getAid());
		if (appointment1 != null)
			return Result.success(appointment1, "Find Matching Appointment!");
		return Result.error("-1", "No Matching Appointment Found.");
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	public List<Appointment> appointmentSearch_M(@RequestParam String keyword) {
		List<Appointment> resulList = new ArrayList<>();
		if (isInteger(keyword)) {
			Appointment resulList_aid = appointmentRepo.findByAid(Integer.valueOf(keyword).intValue());
			List<Appointment> resultList_gid = appointmentRepo.findByGroomer(Integer.valueOf(keyword).intValue());
			List<Appointment> resultList_sid = appointmentRepo.findBySid(Integer.valueOf(keyword).intValue());
			List<Appointment> resultList_price = appointmentRepo.findBytotalPrice(Double.valueOf(keyword));

			if (resulList_aid != null)
				resulList.add(resulList_aid);
			if (resultList_gid != null)
				resulList.addAll(resultList_gid);
			if (resultList_sid != null)
				resulList.addAll(resultList_sid);
			if (resultList_price != null)
				resulList.addAll(resultList_price);

		}
		// List<Appointment> resulList_gname = appointmentRepo.findByGname(keyword);
		// List<Appointment> resulList_servicetype = appointmentRepo.findByService_type(keyword);
		List<Appointment> resulList_status = appointmentRepo.findByStatus(keyword);
		Optional<User> user = userRepo.findByUsernameOptional(keyword);
		try {
			Integer uid = user.get().getUid();
			List<Appointment> resultList_username = appointmentRepo.findByUid(uid);
			if (resultList_username != null)
				resulList.addAll(resultList_username);
		} catch (NoSuchElementException nse) {
			System.out.println(nse.getMessage());
		}
		// System.out.println(uid);

		// if (resulList_gname != null)
		// resulList.addAll(resulList_gname);
		// if (resulList_servicetype != null)
		// 	resulList.addAll(resulList_servicetype);
		if (resulList_status != null)
			resulList.addAll(resulList_status);

		LinkedHashSet<Appointment> hashSet = new LinkedHashSet<>(resulList);
		ArrayList<Appointment> resulList_Final = new ArrayList<>(hashSet);
		return resulList_Final;
	}

	public List<Appointment> getAppointmentList_M() {
		return appointmentRepo.findAll();
	}

	public void updateAppointment(Appointment appointment) {
		
		appointmentRepo.updateStatusByAid(appointment.getAid(), appointment.getStatus());
	}

	public List<Appointment> getAllAppointments() {
		return appointmentRepo.findAll();
	}

	public List<Appointment> findAllSale() {
		return appointmentRepo.findAll();
	}

	public List<Object[]> getAnnualStaticalReport() {
		Map<Integer, Integer> yearMap = new HashMap<>();
		Map<Integer, Double> yearPrice = new HashMap<>();

		List<Integer[]> objList = appointmentRepo.findYearAndCount();
		for (Integer[] obj : objList) {
			int year = obj[0]; // 年份
			int count = obj[1];

			// yearPrice.put(year, );
			yearMap.put(year, count);

			// System.out.println("Here are the years: " + year + " and their counts" + ": "
			// + count);
		}

		for (Integer key : yearMap.keySet()) {

			List<Appointment> yearSales = appointmentRepo.findByYear(key);
			double eachprice = 0.0;
			for (Appointment appointment : yearSales) {
				eachprice += appointment.getTotalprice();
			}

			yearPrice.put(key, eachprice);
			// System.out.println("Here are the key: " + key + " and their eachprice" + ": "
			// + eachprice);
		}

		List<Object[]> list = yearPrice.entrySet().stream()
				.map(e -> new Object[] { e.getKey(), e.getValue() })
				.collect(Collectors.toList());

		// for (Object[] obj : list) {
		// 	System.out.println("Here are the obj: " + obj[0] + " and their price" + ": " + obj[1]);
		// }

		return list;
	}

	public List<Object[]> getQuarterlyStaticalReport(int year) {
		//System.out.println("Here is the year: " + year);

		// List<Appointment> aimYear = appointmentRepo.findByYear(year);

		LocalDateTime FirstMonth = LocalDateTime.of(year, 1, 1, 0, 0, 0);
		LocalDateTime First3Month = LocalDateTime.of(year, 3, 31, 23, 59, 59);
		//System.out.println("Here is the FirstMonth: " + FirstMonth + " and the First3Month: " + First3Month);

		LocalDateTime SecondMonth = LocalDateTime.of(year, 4, 1, 0, 0, 0);
		LocalDateTime Second3Month = LocalDateTime.of(year, 6, 30, 23, 59, 59);

		LocalDateTime ThirdMonth = LocalDateTime.of(year, 7, 1, 0, 0, 0);
		LocalDateTime Third3Month = LocalDateTime.of(year, 9, 30, 23, 59, 59);

		LocalDateTime ForthMonth = LocalDateTime.of(year, 10, 1, 0, 0, 0);
		LocalDateTime Forth3Month = LocalDateTime.of(year, 12, 31, 23, 59, 59);

		Timestamp start1 = Timestamp.valueOf(FirstMonth);
		Timestamp end1 = Timestamp.valueOf(First3Month);
		//System.out.println("Here is the start1: " + start1 + " and the end1: " + end1);

		Timestamp start2 = Timestamp.valueOf(SecondMonth);
		Timestamp end2 = Timestamp.valueOf(Second3Month);

		Timestamp start3 = Timestamp.valueOf(ThirdMonth);
		Timestamp end3 = Timestamp.valueOf(Third3Month);

		Timestamp start4 = Timestamp.valueOf(ForthMonth);
		Timestamp end4 = Timestamp.valueOf(Forth3Month);

		List<Appointment> appointments1 = appointmentRepo.findAllByFinishTimeBetween(start1, end1);
		List<Appointment> appointments2 = appointmentRepo.findAllByFinishTimeBetween(start2, end2);
		List<Appointment> appointments3 = appointmentRepo.findAllByFinishTimeBetween(start3, end3);
		List<Appointment> appointments4 = appointmentRepo.findAllByFinishTimeBetween(start4, end4);

		// System.out.println("Here are the appointments1: " + appointments1);
		// System.out.println("Here are the appointments2: " + appointments2);
		// System.out.println("Here are the appointments3: " + appointments3);
		// System.out.println("Here are the appointments4: " + appointments4);

		Double price1 = 0.0;
		Double price2 = 0.0;
		Double price3 = 0.0;
		Double price4 = 0.0;

		for (Appointment appointment : appointments1) {
			price1 += appointment.getTotalprice();
		}
		for (Appointment appointment : appointments2) {
			price2 += appointment.getTotalprice();
		}
		for (Appointment appointment : appointments3) {
			price3 += appointment.getTotalprice();
		}
		for (Appointment appointment : appointments4) {
			price4 += appointment.getTotalprice();
		}

		Map<Integer, Double> yearMap = new HashMap<>();
		yearMap.put(1, price1);
		yearMap.put(2, price2);
		yearMap.put(3, price3);
		yearMap.put(4, price4);

		List<Object[]> list = yearMap.entrySet().stream()
				.map(e -> new Object[] { e.getKey(), e.getValue() })
				.collect(Collectors.toList());

		// for (Object[] obj : list) {
		// 	System.out.println("Here are the obj: " + obj[0] + " and their price" + ": " + obj[1]);
		// }

		return list;
	}
}
