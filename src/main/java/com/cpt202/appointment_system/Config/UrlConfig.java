package com.cpt202.appointment_system.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class UrlConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

 		//addResourceHandler: the virtual path (url) you want to config
        //addResourceLocations: real path of a file on your server

        registry.addResourceHandler("/g-images/**").addResourceLocations("file:C:\\Users\\Administrator\\Groomer_images\\");
        
        registry.addResourceHandler("/u-images/**").addResourceLocations("file:C:\\Users\\Administrator\\User_images\\");

    }
}

