package com.cpt202.appointment_system.Unit_Test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Repositories.GroomerRepo;
import com.cpt202.appointment_system.Services.GroomerService;

@ExtendWith(MockitoExtension.class)
public class GroomerServiceTest {

    @Mock
    private GroomerRepo groomerRepo;

    @InjectMocks
    private GroomerService groomerService;

    private Groomer groomer;

    @BeforeEach
    void setUp() {
        groomer = new Groomer();
        groomer.setGid(1);
        groomer.setName("Test Groomer");
        groomer.setGender("Male");
        groomer.setImageURL("http://test.com/image");
        groomer.setRank(1);
        groomer.setDescription("Test Description");
        groomer.setPhoneNumber("123456789");
    }

    @Test
    public void testListAllGroomers() {
        List<Groomer> groomerList = new ArrayList<>();
        groomerList.add(groomer);

        when(groomerRepo.findAll()).thenReturn(groomerList);

        List<Groomer> result = groomerService.listAllGroomers();

        Assertions.assertEquals(groomerList, result);
    }

    // @Test
    // public void testViewOneGroomer() {
    //     when(groomerRepo.findByGid(groomer.getGid())).thenReturn(groomer);

    //     Result<?> result = groomerService.viewOneGroomer(groomer.getGid());

    //     Assertions.assertEquals(Result.success(groomer, "Find All Info About This Groomer!"), result);
    // }

    // @Test
    // public void testSearchGroomerByFullID_M() {
    //     when(groomerRepo.findByGid(groomer.getGid())).thenReturn(groomer);

    //     Result<?> result = groomerService.searchGroomerByFullID_M(groomer.getGid());

    //     Assertions.assertEquals(Result.success(groomer, "Find Matching Groomer!"), result);
    // }

    // @Test
    // public void testEditGroomer_M() {
    //     when(groomerRepo.findByGid(groomer.getGid())).thenReturn(groomer);
    //     when(groomerRepo.findByPhoneNumber(groomer.getPhoneNumber())).thenReturn(null);

    //     Result<?> result = groomerService.editGroomer_M(groomer);

    //     Assertions.assertEquals(Result.success(), result);
    // }

    // @Test
    // public void testSearchGroomerByName() {
    //     List<Groomer> groomerList = new ArrayList<>();
    //     groomerList.add(groomer);

    //     when(groomerRepo.findByNameIs(groomer.getName())).thenReturn(groomerList);

    //     Result<?> result = groomerService.searchGroomerByName(groomer);

    //     Assertions.assertEquals(Result.success(groomerList, "Find Matching Appointments!"), result);
    // }

}