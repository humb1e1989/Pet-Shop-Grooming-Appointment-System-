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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cpt202.appointment_system.Repositories.UserRepo;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cpt202.appointment_system.Services.EmailService;

@Controller
@RequestMapping("/appointment-system")

public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserRepo userreposity;
    // @Autowired
    // private AuthenticationManager authenticationManager;
    
    // @GetMapping("")
    // public String showForm() {
    //     return "signup";
    // }

    //登录登录登录
    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Model model,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        if(loginService.loginUser(username, password)==0){
            // 添加用户信息到 session
            User user= userreposity.findByUsername(username);
            Integer Role=user.getType();
            session.setAttribute("user", username);
            session.setAttribute("role", 0);

            redirectAttributes.addFlashAttribute("message", "Login Success");
            return "redirect:/appointment-system";
        } else if(loginService.loginUser(username, password)==1){
            session.setAttribute("user", username);
            session.setAttribute("role", 1);

            redirectAttributes.addFlashAttribute("message", "Administrator Login Success");
            // return "redirect:/maintain/maintainUser";
            return "redirect:/appointment-system";
        }
        
        else {
            redirectAttributes.addFlashAttribute("error", "Login Failed");
            return "redirect:/appointment-system";
        }
    }

    // 注册注册注册
    @PostMapping("/reg")
    public String registerUser(@RequestParam("rename") String username,
                               @RequestParam("reemail") String email,
                               @RequestParam("repass") String password,
                               @RequestParam("repass2") String password2,
                               @RequestParam("phone") String phone,
                               Model model,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {
        // User user = new User(username, password,);
        
        User user=new User(null, username, password, 0, null, "/assets/images/default-user.png", null, phone, email, 0);

        if(loginService.registerUser(user)==1){
            redirectAttributes.addFlashAttribute("error", "注册失败:用户名已被注册或格式不符");
            return "redirect:/appointment-system";
        }
        
        else if(loginService.registerUser(user)==2){
            redirectAttributes.addFlashAttribute("error", "注册失败:邮箱已被注册");
            return "redirect:/appointment-system";
        }
        
        else{

            session.setAttribute("user", username);
            session.setAttribute("role", 0);
            redirectAttributes.addFlashAttribute("message", "注册成功：已为您自动登录");
            
            return "redirect:/appointment-system";

        }
    }

    //登出
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 从会话中移除用户信息
        session.removeAttribute("user");
        session.removeAttribute("role");

        // 重定向到登录页面
        return "redirect:/appointment-system";
    }

//前端检查用户名和email的api接口

    @GetMapping("/check-unique")
    public ResponseEntity<?> checkUnique(@RequestParam("value") String value) {
        boolean isUnique = loginService.checkUnique(value);

        if (isUnique) {
            return ResponseEntity.ok().body("unique");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("duplicate");
        }
    }
    @GetMapping("/check-uniqueem")
    public ResponseEntity<?> checkUniqueem(@RequestParam("value") String value) {
        boolean isUnique = loginService.checkUniqueEmail(value);

        if (isUnique) {
            return ResponseEntity.ok().body("unique");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("duplicate");
        }
    }

    //找回密码
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
            redirectAttributes.addFlashAttribute("error", "用户名不存在，请检查输入是否正确。");
            return "redirect:/reset-password-form";
        }

        String email = user.getEmail();
        int verificationCode = generateVerificationCode();
        verificationCodes.put(username, verificationCode);

        String subject = "重置密码验证码";
        String text = "尊敬的用户 " + username + "，您的重置密码验证码为：" + verificationCode + "。请妥善保管。";

        emailService.sendSimpleMessage(email, subject, text);
        redirectAttributes.addFlashAttribute("success", "验证码已发送至您的邮箱，请注意查收。");
        return "redirect:/reset-password-form";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("username") String username,
                                 @RequestParam("verificationCode") int verificationCode,
                                 @RequestParam("newPassword") String newPassword,
                                 RedirectAttributes redirectAttributes) {
        Integer correctVerificationCode = verificationCodes.get(username);

        if (correctVerificationCode == null || correctVerificationCode != verificationCode) {
            redirectAttributes.addFlashAttribute("error", "验证码错误，请检查输入或重新发送验证码。");
            return "redirect:/reset-password-form";
        }

        User user = userRepository.findByUsername(username);
        user.setPassword(newPassword);
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("success", "密码重置成功，请使用新密码登录。");
        return "redirect:/appointment-system";
    }

}



