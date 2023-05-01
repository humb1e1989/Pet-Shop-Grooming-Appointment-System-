package com.cpt202.appointment_system.Unit_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Controllers.GroomerController;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Services.GroomerService;

public class GroomerControllerTest {

    @Mock
    private GroomerService groomerService;

    @Mock
    private Model model;

    @InjectMocks
    private GroomerController groomerController;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRandomFourGroomers_C() {
        // create a list of groomers
        List<Groomer> gList = new ArrayList<Groomer>();
        Groomer groomer1 = new Groomer();
        groomer1.setGid(1);
        groomer1.setName("Groomer 1");
        gList.add(groomer1);
        Groomer groomer2 = new Groomer();
        groomer2.setGid(2);
        groomer2.setName("Groomer 2");
        gList.add(groomer2);
        Groomer groomer3 = new Groomer();
        groomer3.setGid(3);
        groomer3.setName("Groomer 3");
        gList.add(groomer3);
        Groomer groomer4 = new Groomer();
        groomer4.setGid(4);
        groomer4.setName("Groomer 4");
        gList.add(groomer4);

        // mock the groomerService.listAllGroomers() method
        when(groomerService.listAllGroomers()).thenReturn(gList);

        // call the method to be tested
        String result = groomerController.getRandomFourGroomers_C(model);

        // check the model attributes
        // assertEquals("Groomer 1", model.getAttribute("g0").getClass().getName());
        // assertEquals("Groomer 2", model.getAttribute("g1").getClass().getName());
        // assertEquals("Groomer 3", model.getAttribute("g2").getClass().getName());
        // assertEquals("Groomer 4", model.getAttribute("g3").getClass().getName());
        assertEquals("home", result);
    }
}
