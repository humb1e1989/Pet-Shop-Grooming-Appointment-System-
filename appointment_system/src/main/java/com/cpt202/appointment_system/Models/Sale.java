// package com.cpt202.appointment_system.Models;

// // import javax.persistence.Entity;
// // import javax.persistence.GeneratedValue;
// // import javax.persistence.GenerationType;
// // import javax.persistence.Id;
// // import java.time.LocalDate;

// // @Entity
// // public class Sale {
    
// // }
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;

// import java.sql.Timestamp;
// import java.time.LocalDate;

// @Entity
// public class Sale {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private Timestamp date;
//     private double amount;
    
//     public Sale() {
//     }

//     public Sale(Long id, Timestamp date, double amount) {
//         this.id = id;
//         this.date = date;
//         this.amount = amount;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Timestamp getDate() {
//         return date;
//     }

//     public void setDate(Timestamp date) {
//         this.date = date;
//     }

//     public double getAmount() {
//         return amount;
//     }

//     public void setAmount(double amount) {
//         this.amount = amount;
//     }

//     // 省略构造函数、getter和setter方法
// }