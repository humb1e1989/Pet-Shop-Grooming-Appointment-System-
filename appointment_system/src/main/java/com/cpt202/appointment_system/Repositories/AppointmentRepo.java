package com.cpt202.appointment_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.Appointment;


public interface AppointmentRepo extends JpaRepository<Appointment, Integer>{
    
        // return the appointment list with a specific username
        public List<Appointment> findByFirstnameIs(String username);
}