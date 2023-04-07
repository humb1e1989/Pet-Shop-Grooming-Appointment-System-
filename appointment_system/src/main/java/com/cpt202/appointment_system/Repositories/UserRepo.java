package com.cpt202.appointment_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.User;


public interface UserRepo extends JpaRepository<User, Integer>{
    
}
