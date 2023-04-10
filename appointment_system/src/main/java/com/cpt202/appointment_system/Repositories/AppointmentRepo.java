package com.cpt202.appointment_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.User;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer>{
    
    public List<Appointment> findByUser(User user);
    
    // return the appointment list with a specific username
    public List<Appointment> findByUsernameIs(String username);
    
}
