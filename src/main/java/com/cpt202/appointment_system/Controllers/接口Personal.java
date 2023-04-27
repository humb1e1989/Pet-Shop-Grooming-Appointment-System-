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
@Controller
@RequestMapping("/appointment-system")
public class 接口Personal {

    @GetMapping("/profile")
    public String showUserProfile(HttpSession session, Model model) {
        // 检查用户是否已登录
        String username = (String) session.getAttribute("user");
        if (username == null) {
            // 如果用户未登录，重定向到登录页面
            return "appointment-system";
        }

        // 将用户名添加到模型，以便在页面中显示
        model.addAttribute("username", username);

        // 返回个人主页模板
        return "Signup_profile";
    }
    
}
