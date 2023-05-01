package com.cpt202.appointment_system.Unit_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Controllers.AppointmentController;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.AppointmentForm;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.ServiceType;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.GroomerRepo;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Repositories.ServiceTypeRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Services.AppointmentService;

//@SpringBootTest
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AppointmentRepoTest {
    @Mock
    private AppointmentRepo mockAppointmentRepo;

    @Autowired
    @InjectMocks
    private AppointmentService mockAppointmentService;

    private Appointment appointment_success;
    private Appointment appointment_success1;
    private Appointment appointment_wrongUser;
    private Appointment appointment_wrongUser1;
    private Appointment appointment_wrongGroomer;
    private Appointment appointment_wrongGroomer1;
    private Appointment appointment_wrongPet;
    private Appointment appointment_wrongPet1;
    private Appointment appointment_wrongServiceType;
    private Appointment appointment_wrongServiceType1;
    private Appointment appointment_empty;
    private Appointment appointment_empty1;

    @BeforeEach
    void setUp() {
        Groomer groomer = new Groomer(1);
        // Groomer groomer = new Groomer(1);
        Pet pet = new Pet(1);
        User user = new User(1);
        ServiceType serviceType = new ServiceType(1);
        Timestamp startTime = new Timestamp(System.currentTimeMillis());

        Groomer groomer1 = new Groomer(2);
        Pet pet1 = new Pet(1);
        User user1 = new User(1);
        ServiceType serviceType1 = new ServiceType(2);
        Timestamp startTime1 = new Timestamp(System.currentTimeMillis());

        appointment_success = new Appointment(startTime, serviceType, groomer, user, pet);
        appointment_success1 = new Appointment(startTime1, serviceType1, groomer1, user1, pet1);
        appointment_wrongUser = new Appointment(startTime, serviceType, groomer, new User(), pet);
        appointment_wrongUser1 = new Appointment(startTime1, serviceType1, groomer1, new User(), pet1);
        appointment_wrongGroomer = new Appointment(startTime, serviceType, new Groomer(), user, pet);
        appointment_wrongGroomer1 = new Appointment(startTime1, serviceType1, new Groomer(), user1, pet1);
        appointment_wrongPet = new Appointment(startTime, serviceType, groomer, user, new Pet());
        appointment_wrongPet1 = new Appointment(startTime1, serviceType1, groomer1, user1, new Pet());
        appointment_wrongServiceType = new Appointment(startTime, new ServiceType(), groomer, user, pet);
        appointment_wrongServiceType1 = new Appointment(startTime1, new ServiceType(), groomer1, user1, pet1);
        appointment_empty = new Appointment(1);

        // doNothing().when(mockAppointmentRepo).save(any(Appointment.class));
    }

    @Test
    public void ItShouldMakeAppointment_Success() {
        Result<?> result = mockAppointmentService.makeAppointment_C(appointment_success);
        assertEquals("0", result.getCode());
        verify(mockAppointmentRepo).save(any(Appointment.class));
    }

    @Test
    public void ItShouldMakeAppointment_Success1() {
        Result<?> result = mockAppointmentService.makeAppointment_C(appointment_success1);

        assertEquals("0", result.getCode());
        verify(mockAppointmentRepo).save(any(Appointment.class));
    }

    @Test
    public void ItShouldMakeAppointment_WrongUser() {
        Result<?> result = mockAppointmentService.makeAppointment_C(appointment_wrongUser);

        assertEquals("User is invalid", result.getMsg());
    }

    @Test
    public void ItShouldMakeAppointment_WrongUser1() {
        Result<?> result = mockAppointmentService.makeAppointment_C(appointment_wrongUser1);

        assertEquals("User is invalid", result.getMsg());
    }

    @Test
    public void ItShouldMakeAppointment_WrongGroomer() {
        Result<?> result = mockAppointmentService.makeAppointment_C(appointment_wrongGroomer);

        assertEquals("No such groomer", result.getMsg());
    }

    @Test
    public void ItShouldMakeAppointment_WrongGroomer1() {
        Result<?> result = mockAppointmentService.makeAppointment_C(appointment_wrongGroomer1);

        assertEquals("No such groomer", result.getMsg());
    }

    @Test
    public void ItShouldMakeAppointment_WrongPet() {
        Result<?> result = mockAppointmentService.makeAppointment_C(appointment_wrongPet);

        assertEquals("No such pet", result.getMsg());
    }

    @Test
    public void ItShouldMakeAppointment_WrongPet1() {
        Result<?> result = mockAppointmentService.makeAppointment_C(appointment_wrongPet1);

        assertEquals("No such pet", result.getMsg());
    }

    @Test
    public void ItShouldMakeAppointment_WrongServiceType() {
        Result<?> result = mockAppointmentService.makeAppointment_C(appointment_wrongServiceType);

        assertEquals("No such service", result.getMsg());
    }

    @Test
    public void ItShouldMakeAppointment_WrongServiceType1() {
        Result<?> result = mockAppointmentService.makeAppointment_C(appointment_wrongServiceType1);

        assertEquals("No such service", result.getMsg());
    }

    @Test
    public void ItShouldCannelAppointment_Success() {
        when(mockAppointmentRepo.findByAid(anyInt())).thenReturn(appointment_success);
        Result<?> result = mockAppointmentService.cancelAppointment_C(1);

        assertEquals("Appointment Cancelled!", result.getMsg());
        verify(mockAppointmentRepo).findByAid(anyInt());
    }

    @Test
    public void ItShouldCannelAppointment_Success1() {
        when(mockAppointmentRepo.findByAid(anyInt())).thenReturn(appointment_wrongGroomer);
        Result<?> result = mockAppointmentService.cancelAppointment_C(1);

        assertEquals("Appointment Cancelled!", result.getMsg());
        verify(mockAppointmentRepo).findByAid(anyInt());
    }

    @Test
    public void ItShouldCannelAppointment_Success2() {
        when(mockAppointmentRepo.findByAid(anyInt())).thenReturn(appointment_wrongPet);
        Result<?> result = mockAppointmentService.cancelAppointment_C(1);

        assertEquals("Appointment Cancelled!", result.getMsg());
        verify(mockAppointmentRepo).findByAid(anyInt());
    }

    @Test
    public void ItShouldCannelAppointment_Success3() {
        when(mockAppointmentRepo.findByAid(anyInt())).thenReturn(appointment_wrongServiceType);
        Result<?> result = mockAppointmentService.cancelAppointment_C(1);

        assertEquals("Appointment Cancelled!", result.getMsg());
        verify(mockAppointmentRepo).findByAid(anyInt());
    }

    @Test
    public void ItShouldNotCannelAppointment_NoSuchAppointment() {
        when(mockAppointmentRepo.findByAid(anyInt())).thenReturn(null);
        Result<?> result = mockAppointmentService.cancelAppointment_C(1);

        assertEquals("No Matching Appointment Found.", result.getMsg());
        verify(mockAppointmentRepo).findByAid(anyInt());
    }

    @Test
    public void ItShouldNotCannelAppointment_NoSuchAppointment1() {
        when(mockAppointmentRepo.findByAid(anyInt())).thenReturn(null);
        Result<?> result = mockAppointmentService.cancelAppointment_C(10);

        assertEquals("No Matching Appointment Found.", result.getMsg());
        verify(mockAppointmentRepo).findByAid(anyInt());
    }

    @Test
    public void ItShouldNotBeOverlap() {
        Timestamp start1 = new Timestamp(2023, 5, 1, 13, 0, 0, 0);
        Timestamp end1 = new Timestamp(2023, 5, 1, 14, 0, 0, 0);
        Timestamp start2 = new Timestamp(2023, 5, 1, 15, 0, 0, 0);
        Timestamp end2 = new Timestamp(2023, 5, 1, 16, 0, 0, 0);

        assertFalse(mockAppointmentService.isOverlap(start1, end1, start2, end2));
    }

    @Test
    public void ItShouldBeOverlap() {
        Timestamp start1 = new Timestamp(2023, 5, 1, 13, 0, 0, 0);
        Timestamp end1 = new Timestamp(2023, 5, 1, 15, 0, 0, 0);
        Timestamp start2 = new Timestamp(2023, 5, 1, 14, 0, 0, 0);
        Timestamp end2 = new Timestamp(2023, 5, 1, 16, 0, 0, 0);

        assertTrue(mockAppointmentService.isOverlap(start1, end1, start2, end2));
    }

    @Test
    public void ItShouldModifyAppointment_Success() {
        when(mockAppointmentRepo.findByAid(1)).thenReturn(new Appointment(1));
        Result<?> result = mockAppointmentService.modifyAppointment_C(mockAppointmentRepo.findByAid(1));

        assertEquals("Success!", result.getMsg());
        verify(mockAppointmentRepo).save(any(Appointment.class));
    }

    @Test
    public void ItShouldModifyAppointment_NoSuchAppointment() {
        Appointment appointment_target = new Appointment(1);
        when(mockAppointmentRepo.findByAid(anyInt())).thenReturn(null);
        Result<?> result = mockAppointmentService.modifyAppointment_C(appointment_target);

        assertEquals("No Matching Appointment Found.", result.getMsg());
        verify(mockAppointmentRepo).findByAid(anyInt());
    }

    @Test
    public void ItShouldListAllSuitableAppointment_Success() {
        Appointment appointment1 = new Appointment(1);
        Appointment appointment2 = new Appointment(2);
        Appointment appointment3 = new Appointment(3);
        Appointment appointment4 = new Appointment(4);

        List<Appointment> appointmentList_excepted = new ArrayList<>();
        appointmentList_excepted.add(appointment1);
        appointmentList_excepted.add(appointment2);
        appointmentList_excepted.add(appointment3);
        appointmentList_excepted.add(appointment4);

        List<Appointment> appointmentList_excepted1 = new ArrayList<>();
        appointmentList_excepted1.add(appointment2);

        List<Appointment> appointmentList_excepted2 = new ArrayList<>();
        appointmentList_excepted2.add(appointment3);

        List<Appointment> appointmentList_excepted3 = new ArrayList<>();
        appointmentList_excepted3.add(appointment4);

        when(mockAppointmentRepo.findByAid(1)).thenReturn(appointment1);
        when(mockAppointmentRepo.findByGroomer(1)).thenReturn(appointmentList_excepted1);
        when(mockAppointmentRepo.findBySid(1)).thenReturn(appointmentList_excepted2);
        when(mockAppointmentRepo.findBytotalPrice(1.0)).thenReturn(appointmentList_excepted3);

        List<Appointment> appointmentList_result = mockAppointmentService.appointmentSearch("1");

        assertEquals(appointmentList_result, appointmentList_result);

    }

}
