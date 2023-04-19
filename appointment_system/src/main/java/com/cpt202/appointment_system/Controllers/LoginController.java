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

@Controller
@RequestMapping("/appointment-system")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @GetMapping("/")
    public String showForm() {
        return "signup";
    }

    //登录登录登录
    @PostMapping("/")
    public String loginUser(@RequestParam("uname") String username,
                            @RequestParam("upwd") String password,
                            Model model) {
        if(loginService.loginUser(username, password)){
            model.addAttribute("message", "登录成功");
            return "signup";
        }
        else model.addAttribute("error", "登录失败");
        return "signup";
    }
// 注册注册注册
    @PostMapping("/reg")
    public String registerUser(@RequestParam("rename") String username,
                               @RequestParam("reemail") String email,
                               @RequestParam("repass") String password,
                               @RequestParam("repass2") String password2,
                               @RequestParam("phone") String phone,
                               Model model) {
        // User user = new User(username, password,);
        User user=new User(null, username, password, 0, null, null, null, phone, email, 0);
        // if(password!=password2){
        //     model.addAttribute("error", "注册失败:两次输入密码不相同");
        //     return "log&regiresult";
        // }
        if(loginService.registerUser(user)==1){
            model.addAttribute("error", "注册失败:用户名已被注册或格式不符");
            return "log&regiresult";
        }
        
        else if(loginService.registerUser(user)==2){
            model.addAttribute("error", "注册失败:邮箱已被注册");
            return "log&regiresult";
        }
        
        else if(loginService.registerUser(user)==3){
            model.addAttribute("message", "成啦！");
            return "log&regiresult";
        }
        
        return "log&regiresult";
    }
    
   
    // @PostMapping("/log")
    // public String loginUser(@RequestParam("uname") String username,
    //                         @RequestParam("upwd") String password,
    //                         Model model) {
    //     try {
    //         Authentication authentication = authenticationManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(username, password));
    //         SecurityContextHolder.getContext().setAuthentication(authentication);
    //         model.addAttribute("message", "登录成功");
    //         return "log&regiresult";
    //     } catch (AuthenticationException e) {
    //         model.addAttribute("error", "登录失败");
    //         return "log&regiresult";
    //     }
    // }


}
