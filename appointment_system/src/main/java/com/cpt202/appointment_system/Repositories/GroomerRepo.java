package com.cpt202.appointment_system.Repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.Groomer;


public interface GroomerRepo extends JpaRepository<Groomer, Integer>{

    public Groomer findByGid(Integer gid);
    
    // groomer can be searched by name
    public List<Groomer> findByNameContaining(String name);

    public Groomer findByPhoneNumber(String phoneNumber);

    public List<Groomer> findByNameIs(String name);
    public List<Groomer> findByRankIs(Integer rank);
    public List<Groomer> findByPhoneNumberIs(String phoneNumber);

    public List<Groomer> findAll();

    public Groomer save(Groomer groomer);

}
