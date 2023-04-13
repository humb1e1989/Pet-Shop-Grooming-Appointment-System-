package com.cpt202.appointment_system.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Services.AppointmentService;

@Controller
@RequestMapping("/appointment-system")
public class AppointmentController {

    /*
     * Manager Part
     * This is a part to fullfill all the functions of managers.
     */

    // WJT Manger Part
    @Autowired
    private AppointmentService appointmentService;

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
    @GetMapping("/manager/appointmentList")
    public Result<?> getAllAppointment_M() {
        return appointmentService.getAppointmentList_M();
    }

    // YYY PBI NO.2 - Manager view the appointment detail
    @GetMapping("/manager/appointmentList/view")
    public Result<?> viewAppointment_M(@RequestParam Appointment appointment) {
        return appointmentService.getAppointmentDetail_M(appointment);
    }

    // /*
    //  * Customer Part
    //  * This is a part to fullfill all the functions of customer.
    //  */

    // YYY PBI NO.3 - Customer can view all of history appointments (only his
    // appointment)
    @GetMapping("/customer/appointmentList")
    public Result<?> getAllAppointmentList_C(@RequestParam User user) {
        return appointmentService.getAppointmentBy_Uid(user);
    }

    // TODO : Number the PBI
    // ZYH PBI NO.i Customer can view appointments detail (only their own
    // appointment detail)
    @GetMapping("/customer/appointmentList/view")
    public Result<?> viewAppointment_C(@RequestParam User user) {
        return appointmentService.getAppointmentDetail_C(user);
    }


    // ZYH PBI NO.i Customer can search appointment by user name
    @GetMapping("/customer/appointmentList/search")
    public Result<?> getAppointmentByName_C(@RequestParam String username) {
        return appointmentService.getAppointmentListByUserName_C(username);
    }

    //ZYH PBI NO.ii Customer can cancel appointment
    @PostMapping("/customer/cancelappointment")
    public Result<?> cancelappointment_C(@RequestBody int aid) {
        return appointmentService.cancelAppointment_C(aid);
    }

    // Customer can make appointment
    @PostMapping("/customer/makeappointment")
    public Result<?> makeappointment_C(@RequestBody Appointment appointment) {
        return appointmentService.makeAppointment_C(appointment);
    }

    // Customer can modify appointment
    @PostMapping("/customer/modifyappointment")
    public Result<?> modifyappointment_C(@RequestBody Appointment appointment) {
        return appointmentService.modifyAppointment_C(appointment);
    }

}
