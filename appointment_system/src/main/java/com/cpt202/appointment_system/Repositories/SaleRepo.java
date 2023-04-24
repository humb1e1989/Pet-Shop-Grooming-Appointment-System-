package com.cpt202.appointment_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.Sale;
import com.cpt202.appointment_system.Models.User;


public interface SaleRepo extends JpaRepository<Sale, Integer> {
    //List<Appointment> findAll();

}