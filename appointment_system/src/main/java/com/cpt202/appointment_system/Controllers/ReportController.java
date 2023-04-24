package com.cpt202.appointment_system.Controllers;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import com.cpt202.appointment_system.Common.Result;
// import com.cpt202.appointment_system.Models.Appointment;
// import com.cpt202.appointment_system.Models.Groomer;
// import com.cpt202.appointment_system.Models.Pet;
// import com.cpt202.appointment_system.Models.User;
// import com.cpt202.appointment_system.Repositories.AppointmentRepo;
// import com.cpt202.appointment_system.Repositories.UserRepo;
// import com.cpt202.appointment_system.Services.AppointmentService;
// import com.cpt202.appointment_system.Services.GroomerService;
// import com.cpt202.appointment_system.Services.PetService;
// import com.cpt202.appointment_system.Services.SaleService;

// @Controller
// @RequestMapping("/appointment-system")
// public class SaleController {
//     // Statical Report Part
//     // This is a part to fullfill all the functions of statical report.
//     @Autowired
//     private SaleService saleService;

//     // Get the Quarterly Profit Report and Annual Profit Report
//     // YYY PBI NO.4 - Manager can view the statical report of all appointments
//     @GetMapping("/manager/staticalReport")
//     public String getStaticalReport(Model model) {
//         model.addAttribute("annualStaticalReport", saleService.getAnnualStaticalReport());
//         model.addAttribute("quarterlyStaticalReport", saleService.getQuarterlyStaticalReport());
//         return "staticalReport";
//     }

// }
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpt202.appointment_system.Services.ReportService;

@Controller
@RequestMapping("/appointment-system")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public String showReport(Model model) {
        model.addAttribute("sales", reportService.findAllSale());
        model.addAttribute("annualSales", reportService.getAnnualStaticalReport());
        model.addAttribute("quarterlySales", reportService.getQuarterlyStaticalReport());
        return "report";
    }
}
