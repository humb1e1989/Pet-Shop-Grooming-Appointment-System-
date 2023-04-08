package com.cpt202.appointment_system.Models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int aid;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(columnDefinition = "DATETIME", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @Column(columnDefinition = "DATETIME", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    @Column(columnDefinition = "tinyint", nullable = false)
    private byte status;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String serviceType;

    @Column(nullable = false)
    private int gId;

    @Column(nullable = false)
    private int uId;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelTime;

}
