package com.cpt202.appointment_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.Groomer;


public interface GroomerRepo extends JpaRepository<Groomer, Integer>{
    
}