package com.cpt202.appointment_system.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpSession;

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
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Services.AppointmentService;
import com.cpt202.appointment_system.Services.GroomerService;
import com.cpt202.appointment_system.Services.PetService;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cpt202.appointment_system.Models.AppointmentForm;
import com.cpt202.appointment_system.Models.ServiceType;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.GroomerRepo;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Repositories.ServiceTypeRepo;

import com.cpt202.appointment_system.Services.EmailService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

// @RestController

@Controller
@RequestMapping("/Appointment")
public class AppointmentController {

    /*
     * Manager Part
     * This is a part to fullfill all the functions of managers.
     */

    // WJT Manger Part
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private ServiceTypeRepo servicetypeRepo;

    @Autowired
    private GroomerRepo groomerRepo;

    @Autowired

    private AppointmentService appointmentService;

    @Autowired
    private GroomerService groomerService;

    @Autowired
    private PetService petService;

    @Autowired
    private EmailService emailService;

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
    // // List<Appointment> appointmentList =
    // appointmentService.getAppointmentList_M();
    // // model.addAttribute("appointmentList", appointmentList);
    // }
    @GetMapping("/manager/appointmentList")
    public String getAllAppointment_M(Model model) {// capable of convert a string into object
        // model.addAttribute("appointmentList",
        // appointmentService.listAllAppointments());
        model.addAttribute("appointmentList", appointmentService.listAllAppointments());
        return "allAppointments";
    }

    // YYY PBI NO.2 - Manager view the appointment detail,
    // 这个view可以在customer和manager中通用
    @GetMapping("/appointmentList/view")
    public String getAppointment(Model model, @RequestParam("aid") int aid) {// capable of convert a string into object
        // model.addAttribute("appointmentList",
        // appointmentService.listAllAppointments());
        Appointment appointment = appointmentService.getAppointmentBy_Aid(aid);
        model.addAttribute("appointment", appointment);
        return "appointmentDetail";
    }
    // @PostMapping("/manager/appointmentList/view")
    // public String viewAppointment_M(@RequestParam("appointment") Appointment
    // appointment, Model model) {
    // Appointment appointmentDetail =
    // appointmentService.getAppointmentDetail_M(appointment);
    // model.addAttribute("appointmentDetail", appointmentDetail);

    // return "allAppointments";
    // }

    /*
     * Customer Part
     * This is a part to fullfill all the functions of customer.
     */

    // YYY PBI NO.3 - Customer can view all of history appointments (only
    // hisappointment)
    @GetMapping("/customer/appointmentList")
    public String getUserAppointment_C(Model model) {
        model.addAttribute("appointmentList", appointmentService.listAllAppointments());
        return "allAppointments";
    }

    @GetMapping("/manager/appointmentList/search")
    public String appointmentSearch(Model model) {
        model.addAttribute("keyword", new String());
        return "allAppointments";
    }

    @PostMapping("/manager/appointmentList/search")
    public String appointmentSearchBykey(Model model, @RequestParam("keyword") String keyword) {
        model.addAttribute("appointmentList", appointmentService.appointmentSearch(keyword));
        return "allAppointments";
    }

    // TODO : Number the PBI
    // ZYH PBI NO.i Customer can view appointments detail (only their own
    // appointment detail)
    // @GetMapping("/customer/appointmentList/view")
    // public Result<?> viewAppointment_C(@RequestParam User user) {
    // return appointmentService.getAppointmentDetail_C(user);
    // }

    // ZYH PBI NO.i Customer can search appointment by user name
    @GetMapping("/customer/appointmentList/search")
    public Result<?> getAppointmentByName_C(@RequestParam String username) {

        return appointmentService.getAppointmentListByUserName_C(username);
    }

    // bowenli's pbi

    @GetMapping("/appoint")
    public String makeappointment(HttpSession session, Model model) {
        // 检查用户是否已登录
        String username = (String) session.getAttribute("user");
        if (username == null) {
            // 如果用户未登录，重定向到登录页面
            return "redirect:/appointment-system";
        }
        // save groomer list to the model
        model.addAttribute("appointmentForm", new AppointmentForm());
        List<Groomer> gList = groomerService.listAllGroomers();
        List<Groomer> sortedRankgroomers = new ArrayList<>(gList);
        Collections.sort(sortedRankgroomers, new Comparator<Groomer>() {
            @Override
            public int compare(Groomer g1, Groomer g2) {
                return g2.getRank() - g1.getRank();
            }
        });

        model.addAttribute("gList", gList);
        model.addAttribute("sortedRankgroomers", sortedRankgroomers);

        User user = userRepo.findByUsername(username);

        // save pet to the model
        List<Pet> petList = petService.listAllPets(user);
        List<Pet> petShown = new ArrayList<Pet>();
        for (Pet pet : petList) {
            if (pet.getSize().equals("") && pet.getType().equals("")) {
            } else {
                petShown.add(pet);
            }
        }

        model.addAttribute("petList", petShown);
        // save service list to the model
        List<ServiceType> serviceType = servicetypeRepo.findAll();
        model.addAttribute("serviceType", serviceType);
        return "makeappointment";

    }

    @PostMapping("/appoint")
    public String makeappointment_C(@ModelAttribute("appointmentForm") AppointmentForm appointmentForm,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        Appointment appointment = appointmentForm.toAppointment(Appointment.class);
        String username = (String) session.getAttribute("user");
        Groomer OrderedGroomer = groomerRepo.findByGid(appointmentForm.getGroomer().getGid());
        Pet pet = petRepo.findByPid(appointmentForm.getPet().getPid());
        User user = userRepo.findByUsername(username);
        ServiceType serviceType = servicetypeRepo.findBySid(appointmentForm.getServiceType().getSid());

        appointment.setServiceType(serviceType);
        appointment.setPet(pet);
        appointment.setGroomer(OrderedGroomer);
        appointment.setUser(user);
        double price = 0;
        if (pet.getSize().equals("Small")) {
            price = 10.0 + OrderedGroomer.getRank() * 10 + serviceType.getBasicPrice();
        }
        if (pet.getSize().equals("Medium")) {
            price = 20.0 + OrderedGroomer.getRank() * 10 + serviceType.getBasicPrice();
        }
        if (pet.getSize().equals("Large")) {
            price = 30.0 + OrderedGroomer.getRank() * 10 + serviceType.getBasicPrice();
        }

        session.setAttribute("price", price);

        appointmentService.makeAppointment_C(appointment);
        if (appointmentService.makeAppointment_C(appointment).isSuccess()) {
            try {
                // send email

                String petname = pet.getName();

                String email = user.getEmail();

                Timestamp time = appointment.getStartTime();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // define date format
                String str = sdf.format(time);
                String subject = "Appoint Successfully!";
                String text = "You have successfully appoint the service for your pet " + petname + " at " + time;

                emailService.sendSimpleMessage(email, subject, text);
                return "redirect:/Appointment/payment";
            }

            catch (Exception e) {
                return "redirect:/Appointment/payment";
            }
        }

        else {
            redirectAttributes.addFlashAttribute("error", "The groomer has been appointed");
            return "redirect:/Appointment/appoint";
        }

    }

    @GetMapping("/makeAppointment")
    public String makeAppointment(Model model) {
        return "makeAppointment";
    }

    @GetMapping("/payment")
    public String payment(HttpSession session, Model model) {
        Double price = (Double) session.getAttribute("price");
        model.addAttribute("price", price);
        return "payment";
    }

    // @PostMapping("/customer/makeappointment")
    // public Result<?> makeappointment_C(@RequestBody Appointment appointment) {
    // return appointmentService.makeAppointment_C(appointment);
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
