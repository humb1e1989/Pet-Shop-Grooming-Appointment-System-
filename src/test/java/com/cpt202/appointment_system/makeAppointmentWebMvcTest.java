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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
public class makeAppointmentWebMvcTest {

    // 使用@Autowired注解
    @Autowired
    // 自动注入一个MockMvc对象
    private MockMvc mockMvc;

    // 使用@MockBean注解
    @MockBean
    // 创建一个AppointmentService的模拟对象
    private AppointmentService mockAppointmentService;

    // 使用@Autowired注解
    @Autowired
    // 自动注入GroomerRepo对象
    private GroomerRepo groomerRepo;

    // 使用@Autowired注解
    @Autowired
    // 自动注入PetRepo对象
    private PetRepo petRepo;

    // 使用@Autowired注解
    @Autowired
    // 自动注入UserRepo对象
    private UserRepo userRepo;

    // 使用@Autowired注解
    @Autowired
    // 自动注入ServiceTypeRepo对象
    private ServiceTypeRepo servicetypeRepo;

    @Autowired
    private WebApplicationContext webApplicationContext;

    // 使用@BeforeEach注解
    @BeforeEach
    // 在每个测试方法执行之前执行一些初始化操作
    public void setUp() {
    // 初始化注解
    MockitoAnnotations.initMocks(this);

    // 创建一个Groomer对象
    Groomer groomer = new Groomer(2);
    // 创建一个Pet对象
    Pet pet = new Pet(1);
    // 创建一个User对象
    User user = new User(1);
    // 创建一个ServiceType对象
    ServiceType serviceType = new ServiceType(2);

    // 当groomerRepo调用findByGid方法时，返回groomer对象
    when(groomerRepo.findByGid(2)).thenReturn(groomer);
    // 当petRepo调用findByPid方法时，返回pet对象
    when(petRepo.findByPid(1)).thenReturn(pet);
    // 当userRepo调用findByUsername方法时，返回user对象
    when(userRepo.findByUsername("user")).thenReturn(user);
    // 当servicetypeRepo调用findBySid方法时，返回serviceType对象
    when(servicetypeRepo.findBySid(2)).thenReturn(serviceType);

    // 当mockAppointmentService调用makeAppointment_C方法时，返回一个成功的结果对象
    when(mockAppointmentService.makeAppointment_C(any(Appointment.class)))
    .thenReturn(new Result<>("0", "Appoint successfully!"));

    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    // 使用@Test注解
    @Test
    // 定义一个测试方法，测试预约成功的情况
    public void ItShouldMakeAppointment_Successful() throws Exception {
    // 模拟发送一个POST请求，并且验证响应结果

    mockMvc.perform(post("/appoint/2/1/2/2023-5-1 12:00:00") //模拟发送一个POST请求，指定预约的参数
    .sessionAttr("user", "user")) // 设置session属性，指定用户的用户名为"user"
    .andExpect(status().isOk()) // 验证响应状态码为200，表示请求成功处理了
    .andExpect(content().json("{\"code\":\"0\",\"message\":\"Appointsuccessfully!\"}")); // 验证响应内容为JSON格式，并且与预期的结果对象匹配

    }

}
