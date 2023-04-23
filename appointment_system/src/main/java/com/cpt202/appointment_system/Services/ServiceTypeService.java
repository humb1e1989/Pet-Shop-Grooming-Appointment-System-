package com.cpt202.appointment_system.Services;
import java.util.List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.ServiceType;
import com.cpt202.appointment_system.Repositories.ServiceTypeRepo;

@Service
public class ServiceTypeService {
    
    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    public Result<?> addService_M(ServiceType service) {
        serviceTypeRepo.save(service);
        return Result.success("", "Service added succssfully!");
    }

  
    public Result<?> listService_C() {
        List<ServiceType> serviceList = serviceTypeRepo.findAll();
        return Result.success("", "Service added succssfully!");
    }


    
    public List<ServiceType> getAllService() {
        return serviceTypeRepo.findAll();
    }

    public void updateService(java.security.Provider.Service service) {
        serviceTypeRepo.save(service);
    }

}
