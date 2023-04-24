// package com.cpt202.appointment_system.Controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.cpt202.appointment_system.Common.Result;
// import com.cpt202.appointment_system.Models.ServiceType;
// import com.cpt202.appointment_system.Services.ServiceTypeService;

// @RestController
// @RequestMapping("/ServiceList")
// public class ServiceTypeController {
//     @Autowired
//     private  ServiceTypeService serviceTypeService;

//     //Manager can add a new service to the database
//     @PostMapping("/manager/addService")
//     public Result<?> addService(@RequestBody ServiceType service){
//         return serviceTypeService.addService_M(service);
//     }

//     //Customer can view all the service
//     // @GetMapping("/manager/listService")
//     // public Result<?> listService(@RequestParam ServiceType service){
//     //     return serviceTypeService.listService_C();
//     // }

// }
