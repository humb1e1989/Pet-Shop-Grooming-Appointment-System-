package com.cpt202.appointment_system.Controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.cpt202.appointment_system.Services.UserService;
import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
@RequestMapping("/manager")
public class Maintaincontroller {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String showMaintainPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "maintain";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
        return "redirect:/manager";
    }

    
}

