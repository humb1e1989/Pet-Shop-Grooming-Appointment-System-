package com.cpt202.appointment_system.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.ServiceType;

public interface ServiceTypeRepo extends JpaRepository<ServiceType, Integer>{
    
    public ServiceType  findBySid(Integer sid);

}
