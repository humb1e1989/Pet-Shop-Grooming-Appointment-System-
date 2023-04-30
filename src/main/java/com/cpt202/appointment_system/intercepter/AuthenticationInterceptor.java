package com.cpt202.appointment_system.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;


import org.springframework.web.servlet.ModelAndView;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Models.User;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRepo userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        Integer userType = (Integer)session.getAttribute("role");
        String requestPath = request.getRequestURI();
        if (requestPath.startsWith("/appointment-system")) {
            return true;
        }

        else if (username == null) {
            // 如果用户未登录，重定向到登录页面
            response.sendRedirect("/appointment-system");
            return false;
        } else {
            // User user = userRepository.findByUsername("user");
            // Integer userType=user.getType();
            // String requestPath = request.getRequestURI();
            if (userType == 0 && (requestPath.startsWith("/maintain"))) {
                // 如果是普通用户且试图访问管理员页面，重定向到客户主页
                response.sendRedirect("/customer/profile");
                return false;
            } else if (userType == 1 && (requestPath.startsWith("/customer") || requestPath.startsWith("/Appointment"))) {
                // 如果是管理员且试图访问客户页面，重定向到管理员主页
                response.sendRedirect("/maintain/maintainUser");
                return false;
            }
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}


