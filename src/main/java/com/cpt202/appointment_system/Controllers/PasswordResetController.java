// package com.cpt202.appointment_system.Controllers;

// import com.cpt202.appointment_system.Models.User;
// import com.cpt202.appointment_system.Repositories.UserRepo;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.*;
// import javax.servlet.http.HttpSession;
// import javax.websocket.Session;

// import java.util.HashMap;
// import java.util.Map;
// import java.util.Random;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;
// import com.cpt202.appointment_system.Services.EmailService;

// @Controller
// public class PasswordResetController {
//     @Autowired
//     private EmailService emailService;
//     @Autowired
//     private UserRepo userRepository;

//     private int generateVerificationCode() {
//         Random random = new Random();
//         int min = 100000;
//         int max = 999999;
//         return random.nextInt(max - min + 1) + min;
//     }

//     // 其他已有的代码

//     private Map<String, Integer> verificationCodes = new HashMap<>();

//     @GetMapping("/reset-password-form")
//     public String showResetPasswordForm() {
//         return "reset-password-form";
//     }

//     @PostMapping("/sendForgotPasswordCode")
//     public String resetPassword(@RequestParam("username") String username, RedirectAttributes redirectAttributes) {
//         User user = userRepository.findByUsername(username);

//         if (user == null) {
//             redirectAttributes.addFlashAttribute("error", "用户名不存在，请检查输入是否正确。");
//             return "redirect:/reset-password-form";
//         }

//         String email = user.getEmail();
//         int verificationCode = generateVerificationCode();
//         verificationCodes.put(username, verificationCode);

//         String subject = "重置密码验证码";
//         String text = "尊敬的用户 " + username + "，您的重置密码验证码为：" + verificationCode + "。请妥善保管。";

//         emailService.sendSimpleMessage(email, subject, text);
//         redirectAttributes.addFlashAttribute("success", "验证码已发送至您的邮箱，请注意查收。");
//         return "redirect:/reset-password-form";
//     }

//     @PostMapping("/change-password")
//     public String changePassword(@RequestParam("username") String username,
//                                  @RequestParam("verificationCode") int verificationCode,
//                                  @RequestParam("newPassword") String newPassword,
//                                  RedirectAttributes redirectAttributes) {
//         Integer correctVerificationCode = verificationCodes.get(username);

//         if (correctVerificationCode == null || correctVerificationCode != verificationCode) {
//             redirectAttributes.addFlashAttribute("error", "验证码错误，请检查输入或重新发送验证码。");
//             return "redirect:/reset-password-form";
//         }

//         User user = userRepository.findByUsername(username);
//         user.setPassword(newPassword);
//         userRepository.save(user);

//         redirectAttributes.addFlashAttribute("success", "密码重置成功，请使用新密码登录。");
//         return "redirect:/appointment-system";
//     }

//     // 其他已有的代码
// }
