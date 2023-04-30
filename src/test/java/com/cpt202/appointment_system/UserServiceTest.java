package com.cpt202.appointment_system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Common.UserTool;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.GroomerRepo;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Services.UserService;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private AppointmentRepo appointmentRepo;

    @Mock
    private PetRepo petRepo;

    @Mock
    private GroomerRepo groomerRepo;

    @Mock
    private MultipartFile file;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAllCustomers_M() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUid(1);
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUid(2);
        user2.setUsername("user2");
        userList.add(user1);
        userList.add(user2);

        when(userRepo.findAll()).thenReturn(userList);

        List<User> result = userService.listAllCustomers_M();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getUid()).isEqualTo(1);
        assertThat(result.get(1).getUsername()).isEqualTo("user2");
    }




    @Test
    void testSearchCustomerByName_M() {

    }

    @Test
    void testSearchGroomerByName_C() {

    }

    @Test
    void testUpdateUser() {

    }

    @Test
    void testViewProfile_C() {

    }
}
