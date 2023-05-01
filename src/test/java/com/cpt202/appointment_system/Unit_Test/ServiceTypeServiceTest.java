package com.cpt202.appointment_system.Unit_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.cpt202.appointment_system.Models.ServiceType;

import com.cpt202.appointment_system.Repositories.ServiceTypeRepo;
import com.cpt202.appointment_system.Services.ServiceTypeService;

@ExtendWith(MockitoExtension.class)
public class ServiceTypeServiceTest {

    @Mock
    private ServiceTypeRepo serviceTypeRepo;

    @InjectMocks
    private ServiceTypeService serviceTypeService;

    @Test
    public void testAddService() {
        ServiceType service = new ServiceType();
        // Set up the service object as needed
        serviceTypeService.addService_M(service);
        verify(serviceTypeRepo, times(1)).save(service);
    }

    @Test
    public void testGetAllServiceTypes() {
        List<ServiceType> expectedServiceTypes = new ArrayList<>();
        // Set up the expectedServiceTypes list as needed
        when(serviceTypeRepo.findAll()).thenReturn(expectedServiceTypes);

        List<ServiceType> actualServiceTypes = serviceTypeService.getAllServiceTypes();

        assertEquals(expectedServiceTypes, actualServiceTypes);
    }

    @Test
    public void testUpdateServiceType() {
        ServiceType service = new ServiceType();
        // Set up the service object as needed
        serviceTypeService.updateServiceType(service);
        verify(serviceTypeRepo, times(1)).save(service);
    }

    @Test
    public void testDeleteServiceTypeById() {
        Integer sid = 1;
        serviceTypeService.deleteServiceTypeById(sid);
        verify(serviceTypeRepo, times(1)).deleteById(sid);
    }

    @Test
    public void testSearchServiceTypeByName_M() {
        String name = "example";
        List<ServiceType> expectedServiceTypes = new ArrayList<>();
        // Set up the expectedServiceTypes list as needed
        when(serviceTypeRepo.findByServiceNameContaining(name)).thenReturn(expectedServiceTypes);

        List<ServiceType> actualServiceTypes = serviceTypeService.searchServiceTypeByName_M(name);

        assertEquals(expectedServiceTypes, actualServiceTypes);
    }
}