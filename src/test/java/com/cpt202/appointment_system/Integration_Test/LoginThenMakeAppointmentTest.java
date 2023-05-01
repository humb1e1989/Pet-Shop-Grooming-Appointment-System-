package com.cpt202.appointment_system.Integration_Test;

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
import java.util.Optional;

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
import com.cpt202.appointment_system.Services.LoginService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LoginThenMakeAppointmentTest {
    @Mock
    private UserRepo mockUserRepository;

    @Mock
    private AppointmentRepo mockAppointmentRepo;

    @Autowired
    @InjectMocks
    private LoginService mockLoginService;

    @Autowired
    @InjectMocks
    private AppointmentService mockAppointmentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    // @BeforeEach
    // void setUp() {
    //     Groomer groomer = new Groomer(1);
    //     Pet pet = new Pet(1);
    //     User user = new User(1);
    //     ServiceType serviceType = new ServiceType(1);
    //     Timestamp startTime = new Timestamp(System.currentTimeMillis());

    //     appointment_success = new Appointment(startTime, serviceType, groomer, user, pet);
    //     //doNothing().when(mockAppointmentRepo).save(any(Appointment.class));
    // }

    // @Test
    // public void testLoginThenMakeAppointment_Success() {
    //     Groomer groomer = new Groomer(1);
    //     Pet pet = new Pet(1);
    //     User user = new User(1);
    //     ServiceType serviceType = new ServiceType(1);
    //     Timestamp startTime = new Timestamp(System.currentTimeMillis());

    //     String username = "yyy";
    //     String password = "yyy123";
    //     user.setUsername(username);
    //     user.setPassword(password);
    //     user.setType(0);
    //     Optional<User> dbUser = Optional.of(user);

    //     Appointment appointment_success = new Appointment(startTime, serviceType, groomer, user, pet);

    //     when(mockUserRepository.findByUsernameOptional(username)).thenReturn(dbUser);

    //     // Act
    //     int flag = mockLoginService.loginUser(username, password);

    //     // Assert
    //     assertEquals(0, flag);

    //     Result<?> result = mockAppointmentService.makeAppointment_C(appointment_success);

    //     assertEquals("0", result.getMsg());
    //     verify(mockAppointmentRepo).save(any(Appointment.class));
    // }
}
