package com.cpt202.appointment_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.User;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

    public List<Appointment> findByUser(User user);

    // return the appointment list with a specific username
    public List<Appointment> findByUsernameIs(String username);

    public Appointment findByAid(int aid);

    // WJT
    public List<Appointment> findByserviceType(String servicetype);

    public List<Appointment> findByGroomer(Groomer groomer);

}
