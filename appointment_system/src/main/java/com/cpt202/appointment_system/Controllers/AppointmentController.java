package com.cpt202.appointment_system.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Services.AppointmentService;

@RestController
@RequestMapping("/appointment-system")
public class AppointmentController {
    //WJT Manger Part
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/manager/appointmentList/search/customername")
    public Result<?> getCustomerByName(@RequestParam Appointment appointment){
        return AppointmentService.getAppointmentBy_CName(appointment);
    }

    @GetMapping("/manager/appointmentList/search/service")
    public Result<?> getCustomerByName(@RequestParam Appointment appointment){
        return AppointmentService.getAppointmentBy_Service(appointment);
    }

    @GetMapping("/manager/appointmentList/search/grommer")
    public Result<?> getCustomerByName(@RequestParam Appointment appointment){
        return AppointmentService.getAppointmentBy_GrommerName(appointment);
    }

   
    
    
}
