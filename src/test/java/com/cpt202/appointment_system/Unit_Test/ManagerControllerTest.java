package com.cpt202.appointment_system.Unit_Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.cpt202.appointment_system.Controllers.ManagerController;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.ServiceType;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Services.AppointmentService;
import com.cpt202.appointment_system.Services.GroomerService;
import com.cpt202.appointment_system.Services.ServiceTypeService;
import com.cpt202.appointment_system.Services.UserService;

@ExtendWith(MockitoExtension.class)
public class ManagerControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private GroomerService groomerService;

    @Mock
    private ServiceTypeService serviceTypeService;

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private Model model;

    @InjectMocks
    private ManagerController managerController;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testShowMaintainUserPage() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userService.listAllCustomers_M()).thenReturn(users);

        String result = managerController.showMaintainUserPage(model);
        assert (result.equals("MaintainUser"));
        // also assert the model has the correct attribute set
    }

    @Test
    public void testSearchUserByKey() {
        List<User> userSearchRst = new ArrayList<>();
        userSearchRst.add(new User());
        when(userService.searchCustomerByName_M(any(String.class))).thenReturn(userSearchRst);

        String result = managerController.searchUserByKey(model, "keyword");
        assert (result.equals("MaintainUser"));
        // also assert the model has the correct attribute set
    }

    @Test
    public void testShowMaintainGroomerPage() {
        List<Groomer> groomers = new ArrayList<>();
        groomers.add(new Groomer());
        when(groomerService.listAllGroomers()).thenReturn(groomers);

        String result = managerController.showMaintainGroomerPage(model);
        assert (result.equals("MaintainGroomer"));
        // also assert the model has the correct attribute set
    }

    @Test
    public void testDeleteGroomer() {
        String result = managerController.deleteGroomer(new Groomer());
        assert (result.equals("redirect:/maintain/maintainGroomer"));
    }

    @Test
    public void testUpdateGroomer() {
        String result = managerController.updateGroomer(new Groomer());
        assert (result.equals("redirect:/maintain/maintainGroomer"));
    }

    @Test
    public void testSearchGroomer() {
        List<Groomer> groomers = new ArrayList<>();
        groomers.add(new Groomer());
        when(groomerService.searchGroomerByName_M(any(String.class))).thenReturn(groomers);

        String result = managerController.searchGroomer(model, "keyword");
        assert (result.equals("MaintainGroomer"));
        // also assert the model has the correct attribute set
    }
}
