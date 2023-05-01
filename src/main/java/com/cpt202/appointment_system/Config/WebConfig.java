package com.cpt202.appointment_system.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.cpt202.appointment_system.intercepter.AuthenticationInterceptor;;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/appointment-system**") // 排除登录页面
                // .excludePathPatterns("/appointment-system/login") // 排除登录处理路径
                .excludePathPatterns("/assets/css/**") // 排除 CSS 静态资源
                .excludePathPatterns("/assets/js/**") // 排除 JavaScript 静态资源
                .excludePathPatterns("/assets/images/**") ;// 排除图片静态资源
                // .excludePathPatterns("/assets/fonts/**") ;
    }
}