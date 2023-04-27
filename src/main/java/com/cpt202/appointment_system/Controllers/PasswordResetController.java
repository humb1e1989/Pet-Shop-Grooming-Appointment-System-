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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cpt202.appointment_system.Services.EmailService;

@Controller
public class PasswordResetController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepo userRepository;

    private int generateVerificationCode() {
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        return random.nextInt(max - min + 1) + min;
    }

    // 其他已有的代码

    private Map<String, Integer> verificationCodes = new HashMap<>();

    @GetMapping("/reset-password-form")
    public String showResetPasswordForm() {
        return "reset-password-form";
    }

    @PostMapping("/sendForgotPasswordCode")
    public String resetPassword(@RequestParam("username") String username, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Username does not exist, please check whether the input is correct.");
            return "redirect:/reset-password-form";
        }

        String email = user.getEmail();
        int verificationCode = generateVerificationCode();
        verificationCodes.put(username, verificationCode);

        String subject = "Reset Password Verification Code";
        String text = "Dear " + username + "，Your reset password verification code is：" + verificationCode + ". Please keep it safe.";

        emailService.sendSimpleMessage(email, subject, text);
        redirectAttributes.addFlashAttribute("success", "The verification code has been sent to your email, please pay attention to check it.");
        return "redirect:/reset-password-form";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("username") String username,
                                 @RequestParam("verificationCode") int verificationCode,
                                 @RequestParam("newPassword") String newPassword,
                                 RedirectAttributes redirectAttributes) {
        Integer correctVerificationCode = verificationCodes.get(username);

        if (correctVerificationCode == null || correctVerificationCode != verificationCode) {
            redirectAttributes.addFlashAttribute("error", "The verification code is wrong, please check the input or resend the verification code.");
            return "redirect:/reset-password-form";
        }

        User user = userRepository.findByUsername(username);
        user.setPassword(newPassword);
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("success", "The password has been reset successfully, please log in with the new password.");
        return "redirect:/appointment-system";
    }

    // 其他已有的代码
}
