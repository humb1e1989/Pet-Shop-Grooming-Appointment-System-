package com.cpt202.appointment_system.Models;

import java.util.List;

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


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }
    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
    public List<Pet> getPetList() {
        return petList;
    }
    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    
}
