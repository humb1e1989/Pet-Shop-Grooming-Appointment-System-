// package com.cpt202.appointment_system.Services;

// // import java.sql.Date;
// // import java.sql.Timestamp;
// // import java.text.SimpleDateFormat;
// // import java.util.ArrayList;
// // import java.util.Calendar;
// // import java.util.LinkedHashSet;
// // import java.util.List;
// // import java.util.regex.Pattern;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.stereotype.Service;
// // import org.springframework.web.bind.annotation.RequestParam;

// // import com.cpt202.appointment_system.Common.Result;
// // import com.cpt202.appointment_system.Models.Appointment;
// // import com.cpt202.appointment_system.Models.Groomer;
// // import com.cpt202.appointment_system.Models.Pet;
// // import com.cpt202.appointment_system.Models.User;
// // import com.cpt202.appointment_system.Repositories.AppointmentRepo;
// // import com.cpt202.appointment_system.Repositories.GroomerRepo;
// // import com.cpt202.appointment_system.Repositories.PetRepo;
// // import com.cpt202.appointment_system.Repositories.SaleRepo;
// // import com.cpt202.appointment_system.Repositories.UserRepo;

// // public class SaleService {
// //     @Autowired
// //     private SaleRepo saleRepo;
// //     // Statical Report Part
// //     // This is a part to fullfill all the functions of statical report.

// //     // Get the Quarterly Profit Report and Annual Profit Report
// //     // YYY PBI NO.4 - Manager can view the statical report of all appointments
// //     public Double getAnnualStaticalReport() {
// //         List<Appointment> appointments = saleRepo.findAll();
// //         Appointment[] appoData = appointments.toArray(new Appointment[appointments.size()]);
// //         Double[] price = new Double[appointments.size()];
// //         Timestamp[] finishTime = new Timestamp[appointments.size()];
// //         for (int i = 0; i < appointments.size(); i++) {
// //             price[i] = appoData[i].getTotalprice();
// //             finishTime[i] = appoData[i].getFinishTime();
// //         }

// //         Table annualReportTable = Table.create("学生分数统计表").addColumns(
// //                 DoubleColumn.create("Price", price),
// //                 DoubleColumn.create("Date", finishTime));

// //         Plot.show(AreaPlot.create("Test", annualReportTable, "Date", "Price"));

// //         return 0.0;
// //     }

// //     public List<Double> getQuarterlyStaticalReport() {

// //         return null;
// //     }
// // }
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.cpt202.appointment_system.Models.Sale;
// import com.cpt202.appointment_system.Repositories.SaleRepo;

// import java.sql.Timestamp;
// import java.util.List;

// @Service
// public class ReportService {

//     @Autowired
//     private SaleRepo saleRepository;

//     public List<Sale> findAllSale() {
//         return saleRepository.findAll();
//     }

//     public Double getAnnualStaticalReport() {
//         List<Sale> appointments = saleRepository.findAll();
//         Sale[] appoData = appointments.toArray(new Sale[appointments.size()]);
//         Double[] price = new Double[appointments.size()];
//         Timestamp[] finishTime = new Timestamp[appointments.size()];
//         for (int i = 0; i < appointments.size(); i++) {
//             price[i] = appoData[i].getAmount();
//             finishTime[i] = appoData[i].getDate();
//         }

//         // Table annualReportTable = Table.create("学生分数统计表").addColumns(
//         //         DoubleColumn.create("Price", price),
//         //         DoubleColumn.create("Date", finishTime));

//         // Plot.show(AreaPlot.create("Test", annualReportTable, "Date", "Price"));

//         return 0.0;
//     }

//     public List<Double> getQuarterlyStaticalReport() {

//         return null;
//     }
// }