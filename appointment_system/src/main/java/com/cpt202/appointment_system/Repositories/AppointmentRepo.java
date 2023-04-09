package com.cpt202.appointment_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.Appointment;
<<<<<<< HEAD
import com.cpt202.appointment_system.Models.User;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer>{
    
    public List<Appointment> findByUser(User user);
    
}
=======


public interface AppointmentRepo extends JpaRepository<Appointment, Integer>{
    
        // return the appointment list with a specific username
        public List<Appointment> findByFirstnameIs(String username);
}
>>>>>>> a7d40deda916c94fd3cc9bb227f0b6eafde1f5c5
