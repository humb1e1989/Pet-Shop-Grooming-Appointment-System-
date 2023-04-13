package com.cpt202.appointment_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.User;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

    public List<Appointment> findByUser(User user);

    // Appointment can be searched by username
    //ZYH TODO: I do not realy know how to form the function name (if any problem, please change it)
    public List<Appointment> findByUserUsernameContaining(String username);

    public Appointment findByAid(Integer aid);

    // WJT
    public List<Appointment> findByserviceType(String servicetype);

    public List<Appointment> findByGroomer(Groomer groomer);

}
