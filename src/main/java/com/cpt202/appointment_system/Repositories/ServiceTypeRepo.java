package com.cpt202.appointment_system.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.List;

import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.ServiceType;

public interface ServiceTypeRepo extends JpaRepository<ServiceType, Integer>{
    
    public ServiceType  findBySid(Integer sid);

    public List<ServiceType> findAll();

    public List<ServiceType> findByServiceName(String serviceName);

    @SuppressWarnings("unchecked")
    public ServiceType save(ServiceType service);

    public void deleteById(Integer sid);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM service_type a WHERE LOWER(a.service_name) like %?1%", nativeQuery = true)
    public List<ServiceType> findByServiceNameContaining(@Param("keyword") String keyword);

}
