package com.cpt202.appointment_system;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.GroomerRepo;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Services.UserService;



// @SpringJUnitWebConfig
@ExtendWith(MockitoExtension.class) 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ManagerControllerIntegrationTest {
   
    
        @Autowired
        private TestRestTemplate restTemplate;
    
        @Autowired
        private UserService userService;
    
        @Autowired
        private UserRepo userRepo;
    
        @Autowired
        private AppointmentRepo appointmentRepo;
    
        @Autowired
        private PetRepo petRepo;
    
        @Autowired
        private GroomerRepo groomerRepo;
    
        @BeforeEach
        void setUp() {
            // create test data
            User user1 = new User("testuser1", "password", 0);
            User user2 = new User("testuser2", "password", 0);
            User user3 = new User("admin", "password", 1);
    
            userRepo.saveAll(Arrays.asList(user1, user2, user3));
        }
    
        @AfterEach
        void tearDown() {
            // clean up test data
            userRepo.deleteAll();
            appointmentRepo.deleteAll();
            petRepo.deleteAll();
            groomerRepo.deleteAll();
        }
    
        // @Test
        // void testMaintainUserPage() {
        //     // login as administrator
        //     HttpHeaders headers = new HttpHeaders();
        //     headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //     MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        //     map.add("username", "admin");
        //     map.add("password", "password");
        //     HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        //     ResponseEntity<String> loginResponse = restTemplate.postForEntity("/login", request, String.class);
    
        //     // check login success
        //     assertEquals(HttpStatus.FOUND, loginResponse.getStatusCode());
        //     HttpHeaders loginHeaders = loginResponse.getHeaders();
        //     assertTrue(loginHeaders.getLocation().toString().endsWith("/appointment-system"));
    
        //     // get maintain user page
        //     ResponseEntity<String> maintainUserResponse = restTemplate.getForEntity("/maintain/maintainUser", String.class);
    
        //     // check response status and content
        //     assertEquals(HttpStatus.OK, maintainUserResponse.getStatusCode());
        //     assertTrue(maintainUserResponse.getBody().contains("<h1>Maintain User</h1>"));
    
        //     // check list all customers
        //     // List<User> expectedUsers = userService.listAllCustomers_M();
        //     // Model expectedModel = new ExtendedModelMap();
        //     // expectedModel.addAttribute("users", expectedUsers);
        //     // String expectedContent = ThymeleafTestUtils.process("/templates/MaintainUser.html", expectedModel);
        //     // assertTrue(maintainUserResponse.getBody().contains(expectedContent));
        // }

}
