package com.cpt202.appointment_system.Repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.Groomer;


public interface GroomerRepo extends JpaRepository<Groomer, Integer>{

    public Groomer findByGid(Integer gid);
    
    public List<Groomer> findByNameContaining(String name);

    public Groomer findByPhoneNumber(String phoneNumber);

}
