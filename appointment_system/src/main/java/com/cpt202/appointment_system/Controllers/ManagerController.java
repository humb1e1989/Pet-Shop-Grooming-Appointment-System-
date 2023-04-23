package com.cpt202.appointment_system.Controllers;

import java.security.Provider.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.ServiceType;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Services.GroomerService;
import com.cpt202.appointment_system.Services.UserService;
import com.cpt202.appointment_system.Services.ServiceTypeService;
import com.cpt202.appointment_system.Services.AppointmentService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private UserService userService;

    private GroomerService groomerService;

    private ServiceTypeService ServiceTypeService;

    private AppointmentService AppointmentService;

    @GetMapping("/maintain")
    public String showUserMaintainPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "maintain";
    }

    @GetMapping("/maintain")
    public String showGroomerMaintainPage(Model model) {
        List<Groomer> groomers = groomerService.listAllGroomers();
        model.addAttribute("groomers", groomers);
        return "maintain";
    }

    @GetMapping("/maintain")
    public String showServiceTypeMaintainPage(Model model) {
        List<ServiceType> services = ServiceTypeService.getAllService();
        model.addAttribute("services", services);
        return "maintain";

    }

    @GetMapping("/maintain")
    public String showAppointmentMaintainPage(Model model) {
        List<Appointment> appointments = AppointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "maintain";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
        return "redirect:/manager/maintain";
    }

    @PostMapping("/update")
    public String updateGroomer(@ModelAttribute("groomer") Groomer groomer, RedirectAttributes redirectAttributes) {
        groomerService.updateGroomer(groomer);
        redirectAttributes.addFlashAttribute("successMessage", "Groomer updated successfully");
        return "redirect:/manager/maintain";
    }

    @PostMapping("/update")
    public String updateGroomer(@ModelAttribute("services") ServiceType service, RedirectAttributes redirectAttributes) {
        ServiceTypeService.updateService(service);
        redirectAttributes.addFlashAttribute("successMessage", "Service updated successfully");
        return "redirect:/manager/maintain";
    }

    @PostMapping("/update")
    public String updateAppointment(@ModelAttribute("appointments") Appointment appointment,
            RedirectAttributes redirectAttributes) {
        AppointmentService.editAppointment_C(appointment);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment updated successfully");
        return "redirect:/manager/maintain";
    }
}