package com.cpt202.appointment_system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
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




@SpringBootTest
public class AppointmentRepoTest {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AppointmentRepo appointmentRepo;

    @Test
    public void makeAppointment_Test() {
        // given
        GroomerRepo mockGroomerRepo = mock(GroomerRepo.class);
        PetRepo mockPetRepo = mock(PetRepo.class);
        UserRepo mockUserRepo = mock(UserRepo.class);
        ServiceTypeRepo mockServiceTypeRepo = mock(ServiceTypeRepo.class);
        AppointmentRepo mockAppointmentRepo = mock(AppointmentRepo.class);

        AppointmentService mockAppointmentService = mock(AppointmentService.class);

        when(mockGroomerRepo.findByGid(anyInt())).thenReturn(new Groomer(2));
        when(mockPetRepo.findByPid(anyInt())).thenReturn(new Pet(1));
        when(mockUserRepo.findByUid(anyInt())).thenReturn(new User(1));
        when(mockServiceTypeRepo.findBySid(anyInt())).thenReturn(new ServiceType(2));

        // when
        Groomer groomer = mockGroomerRepo.findByGid(1);
        Pet pet = mockPetRepo.findByPid(1);
        User user = mockUserRepo.findByUid(1);
        ServiceType serviceType = mockServiceTypeRepo.findBySid(1);
        Timestamp startTime = new Timestamp(System.currentTimeMillis());

        Appointment appointment = new Appointment(startTime, serviceType, groomer, user, pet);
        //when(mockAppointmentService.makeAppointment_C(appointment)).thenReturn(Result.success());

        Result<?> result = mockAppointmentService.makeAppointment_C(appointment);

        System.out.println(result.getCode() + "" + result.getMsg());

        // then
        assertEquals("0", result.getCode());
        verify(mockAppointmentRepo).save(appointment);
    }

}
