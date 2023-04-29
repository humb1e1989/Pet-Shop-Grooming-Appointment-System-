package com.cpt202.appointment_system.Services;
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

    // add a new service
    public Result<?> addService_M(ServiceType service) {
        serviceTypeRepo.save(service);
        return Result.success("", "Service added succssfully!");
    }
    
    // get all services
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepo.findAll();
    }

    // public void updateService(java.security.Provider.Service service) {
    //     serviceTypeRepo.save(service);
    // }

    // edit service
    public void updateServiceType(ServiceType service) {
        serviceTypeRepo.save(service);
    }

    public void deleteServiceTypeById(Integer sid) {
        serviceTypeRepo.deleteById(sid);
    }

    public List<ServiceType> searchServiceTypeByName_M(String name) {
        return serviceTypeRepo.findByServiceNameContaining(name);
    }

}