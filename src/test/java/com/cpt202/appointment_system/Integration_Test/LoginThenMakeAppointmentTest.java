package com.cpt202.appointment_system.Integration_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.ServiceType;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Services.AppointmentService;
import com.cpt202.appointment_system.Services.LoginService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LoginThenMakeAppointmentTest {
    @Mock
    private UserRepo mockUserRepo;

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

    Appointment appointment_success;

    @BeforeEach
    public void setUp() {
        Timestamp registrationTime = new Timestamp(2023, 4, 28, 17, 38, 49, 0);
        User user1 = new User();
        new User(1, "yyy", "yyy123", 0, registrationTime, "", "male", "13345678901", "email@gmail.com", 0);
        Groomer groomer = new Groomer(1);
        // Groomer groomer = new Groomer(1);
        Pet pet = new Pet(1);
        ServiceType serviceType = new ServiceType(1);
        Timestamp startTime = new Timestamp(System.currentTimeMillis());

        appointment_success = new Appointment(startTime, serviceType, groomer, user1, pet);
    }

    @Test
    public void testLoginThenMakeAppointment_Success() {
        String username = "user123";
        String password = "password";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setType(0);
        Optional<User> dbUser = Optional.of(user);
        when(mockUserRepo.findByUsernameOptional(username)).thenReturn(dbUser);

        // Act
        int result = mockLoginService.loginUser(username, password);

        // Assert
        assertEquals(0, result);

        when(mockUserRepo.findByUid(user.getUid())).thenReturn(user);
        Result<?> result2 = mockAppointmentService.makeAppointment_C(appointment_success);

        assertEquals("0", result2.getCode());
        verify(mockAppointmentRepo).save(any(Appointment.class));
        // verify(mockAppointmentRepo).save(any(Appointment.class));
    }
}
