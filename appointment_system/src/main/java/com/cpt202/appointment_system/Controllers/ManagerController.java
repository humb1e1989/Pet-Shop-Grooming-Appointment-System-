package com.cpt202.appointment_system.Controllers;

import java.security.Provider.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.ServiceType;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.GroomerRepo;
import com.cpt202.appointment_system.Services.GroomerService;
import com.cpt202.appointment_system.Services.UserService;
import com.cpt202.appointment_system.Services.ServiceTypeService;
import com.cpt202.appointment_system.Services.AppointmentService;

// @Controller
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroomerService groomerService;

    @Autowired
    private ServiceTypeService ServiceTypeService;

    @Autowired
    private AppointmentService AppointmentService;

    @Autowired
    private GroomerRepo GroomerRepo;

    @GetMapping("")
    public String showMaintainPage(Model model) {
        List<User> users = userService.listAllCustomers_M();
        model.addAttribute("users", users);
        List<Groomer> groomers = groomerService.listAllGroomers();
        model.addAttribute("groomers", groomers);
        List<ServiceType> services = ServiceTypeService.getAllServiceTypes();
        model.addAttribute("serviceTypes", services);
<<<<<<< HEAD
        List<Appointment> appointments = AppointmentService.getAllAppointments();
=======
        List<Appointment> appointments = AppointmentService.listAllAppointments();
>>>>>>> 3c85a2bbec2863bdff13817cbb1e386df6413547
        model.addAttribute("appointments", appointments);
        return "manager";
    }

    // @GetMapping("/delete")
    // public Result<?> deleteUser1(@RequestParam("uid") Integer uid) {
    //     userService.deleteUserById(uid);
    //     // return "redirect:/appointment-system/manager";
    //     return Result.success();
    // }

    // Manager can delete customer by id
    // @PostMapping("/delete")
    // public Result<?> deleteUser(@RequestParam("uid") Integer uid) {
    //     userService.deleteUserById(uid);
    //     // return "redirect:/appointment-system/manager";
    //     return Result.success();
    // }


    // @GetMapping("/delete")
    // public String deleteTU(Model model) {
    //     model.addAttribute("user", new User());
    //     return "manager";
    // }

    // for testing
    @PostMapping("/deleteUser")
    public String deleteUser(@ModelAttribute User user) {
        Integer uid = user.getUid();
        userService.deleteUserById(uid);
        return "redirect:/manager";
        // return Result.success();
    }

    @PostMapping("/deleteGroomer")
    public String deleteGroomer(@ModelAttribute Groomer groomer) {
        Integer gid = groomer.getGid();
        groomerService.deleteGroomerById(gid);
        return "redirect:/manager";
    }

    @PostMapping("/updateGroomer")
    public String updateGroomer(@ModelAttribute Groomer groomer) {
        groomerService.editGroomer_M(groomer);
        return "redirect:/manager";
    }

    @PostMapping("/deleteServiceType")
    public String deleteServiceType(@ModelAttribute ServiceType service) {
        Integer sid = service.getSid();
        ServiceTypeService.deleteServiceTypeById(sid);
        return "redirect:/manager";
    }

    @PostMapping("/updateServiceType")
    public String updateServiceType(@ModelAttribute ServiceType service) {
        ServiceTypeService.editServiceType(service);
        return "redirect:/manager";
    }

    // @PostMapping("/user")
    // public String updateUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
    //     userService.updateUser(user);
    //     redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
    //     return "redirect:/manager/maintain";
    // }

    // @PostMapping("/manager")
    // public String updateGroomer(@ModelAttribute("groomer") Groomer groomer,RedirectAttributes redirectAttributes) {
    //     // Groomer groomer1 = GroomerRepo.findByGid(gid);
    //     groomerService.editGroomer_M(groomer);

    //     redirectAttributes.addFlashAttribute("successMessage", "Groomer updated successfully");
    //     return "redirect:/manager";
    // }

    // @PostMapping("/serviceType")
    // public String updateServiceType(@ModelAttribute("services") ServiceType service, RedirectAttributes redirectAttributes) {
    //     ServiceTypeService.editServiceType(service);
    //     redirectAttributes.addFlashAttribute("successMessage", "Service updated successfully");
    //     return "redirect:/manager";
    // }

    // @PostMapping("/appointment")
    // public String updateAppointment(@ModelAttribute("appointments") Appointment appointment,
    //         RedirectAttributes redirectAttributes) {
    //     AppointmentService.editAppointment_C(appointment);
    //     redirectAttributes.addFlashAttribute("successMessage", "Appointment updated successfully");
    //     return "redirect:/manager";
    // }
}