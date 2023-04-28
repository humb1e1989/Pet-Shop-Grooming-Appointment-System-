package com.cpt202.appointment_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;

public interface PetRepo extends JpaRepository<Pet, Integer>{
    
    public List<Pet> findByUser(User user);

    public Pet  findByPid(Integer pid);
    
}
