package com.cpt202.appointment_system.Models;

import java.util.List;

import lombok.Data;

@Data
public class UserTool {

    private User user;
    private List<Appointment> appointmentList;
    private List<Pet> petList;

    
    public UserTool() {
    }

    public UserTool(User user, List<Appointment> appointmentList, List<Pet> petList) {
        this.user = user;
        this.appointmentList = appointmentList;
        this.petList = petList;
    }

    
    
}
