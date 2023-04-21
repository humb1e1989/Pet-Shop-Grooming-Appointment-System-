package com.cpt202.appointment_system.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Services.AppointmentService;
import com.cpt202.appointment_system.Services.GroomerService;

// @RestController
@Controller
@RequestMapping("/appointment-system")
public class AppointmentController {

    /*
     * Manager Part
     * This is a part to fullfill all the functions of managers.
     */

    // WJT Manger Part
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private GroomerService groomerService;

    @GetMapping("/manager/appointmentList/search/customername")
    public List<Appointment> getAppointmentByName(@RequestParam String customerName) {
        return appointmentService.getAppointmentBy_CName(customerName);
    }

    @GetMapping("/manager/appointmentList/search/service")
    public List<Appointment> getAppointmentByService(@RequestParam String service) {
        return appointmentService.getAppointmentBy_Service(service);
    }

    @GetMapping("/manager/appointmentList/search/grommer")
    public List<Appointment> getAppointmentByGrommer(@RequestParam String groomerName) {
        return appointmentService.getAppointmentBy_GroomerName(groomerName);
    }

    // YYY PBI NO.1 - Manager can view all of appointments
    // @GetMapping("/manager/appointmentList")
    // public String getAllAppointment_M(Model model) {
    //     // List<Appointment> appointmentList = appointmentService.getAppointmentList_M();
    //     // model.addAttribute("appointmentList", appointmentList);
    // }
    @GetMapping("/manager/appointmentList")
    public String getAllAppointment_M(Model model) {// capable of convert a string into object
        // model.addAttribute("appointmentList", appointmentService.listAllAppointments());
        model.addAttribute("appointmentList", appointmentService.listAllAppointments());
        return "allAppointments";
    }

    // YYY PBI NO.2 - Manager view the appointment detail
    @GetMapping("/manager/appointmentList/view")
    public String getAppointment(Model model) {// capable of convert a string into object
        // model.addAttribute("appointmentList", appointmentService.listAllAppointments());
        model.addAttribute("appointment", new Appointment(1));
        return "appointmentDetail";
    }
    @PostMapping("/manager/appointmentList/view")
    public String viewAppointment_M(@RequestParam("appointment") Appointment appointment, Model model) {
        Appointment appointmentDetail = appointmentService.getAppointmentDetail_M(appointment);
        model.addAttribute("appointmentDetail", appointmentDetail);

        return "allAppointments";
    }

    /*
     * Customer Part
     * This is a part to fullfill all the functions of customer.
     */

    //YYY PBI NO.3 - Customer can view all of history appointments (only hisappointment)
    @GetMapping("/customer/appointmentList")
    public String getUserAppointment_C(Model model) {
        model.addAttribute("appointmentList", appointmentService.listAllAppointments());
        return "allAppointments";
    }

    // TODO : Number the PBI
    // ZYH PBI NO.i Customer can view appointments detail (only their own
    // appointment detail)
    // @GetMapping("/customer/appointmentList/view")
    // public Result<?> viewAppointment_C(@RequestParam User user) {
    //     return appointmentService.getAppointmentDetail_C(user);
    // }

    // ZYH PBI NO.i Customer can search appointment by user name
    @GetMapping("/customer/appointmentList/search")
    public Result<?> getAppointmentByName_C(@RequestParam String username) {
        return appointmentService.getAppointmentListByUserName_C(username);
    }
    
    //bowenli's pbi

    //Customer can make appointment
    // {"name": "asas", "pet": "宠物1", "serviceType": "Pet Bathing", "startTime": "2023/05/04 00:44", "groomer": "Groomer1"}
    @GetMapping("/customer/makeappointment")
    public String makeappointment(Model model) {// capable of convert a string into object
        model.addAttribute("appointment", new Appointment());
        return "makeappointment";
    }

    @PostMapping("/customer/makeappointment")
    public String makeappointment_C(@ModelAttribute("appointment") Appointment appointment) {
         appointmentService.makeAppointment_C(appointment);
         return "home";
    }

    // @PostMapping("/customer/makeappointment")
    // public Result<?> makeappointment_C(@RequestBody Appointment appointment) {
    //      return  appointmentService.makeAppointment_C(appointment);
    // }
    

    // Customer can cancel appointment
    @PostMapping("/customer/cancelAppointment")
    public Result<?> cancelAppointment_C(@RequestBody int aid) {
        return appointmentService.cancelAppointment_C(aid);
    }

    // Customer can modify appointment
    @PostMapping("/customer/editAppointment")
    public Result<?> editAppointment_C(@RequestBody Appointment appointment) {
        return appointmentService.modifyAppointment_C(appointment);
    }


    
    
}
