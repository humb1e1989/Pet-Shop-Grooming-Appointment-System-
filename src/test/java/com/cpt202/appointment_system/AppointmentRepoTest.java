package com.cpt202.appointment_system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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

    private Appointment appointment;

    @BeforeEach
    void setUp() {
        Groomer groomer = new Groomer(1);
        //Groomer groomer = new Groomer(1);
        Pet pet = new Pet(1);
        User user = new User(1);
        ServiceType serviceType = new ServiceType(1);
        Timestamp startTime = new Timestamp(System.currentTimeMillis());

        appointment = new Appointment(startTime, serviceType, groomer,
        user, pet);

        //doNothing().when(mockAppointmentRepo).save(any(Appointment.class));
    }

    @Test
    public void ItShouldMakeAppointment_Successful() {
        //when(mockAppointmentService.makeAppointment_C(appointment)).thenAnswer(invocation -> Result.success("0", "Success!"));

        Result<?> result = mockAppointmentService.makeAppointment_C(appointment);

        System.out.println("Here is the result: " + result);

        // then
        assertEquals("0", result.getCode());
        verify(mockAppointmentRepo).save(any(Appointment.class));
    }

}
