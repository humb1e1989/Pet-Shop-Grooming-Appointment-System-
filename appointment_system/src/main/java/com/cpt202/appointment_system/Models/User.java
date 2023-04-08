package com.cpt202.appointment_system.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uid;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String username;

    @Column(columnDefinition = "varchar(15)", nullable = false)
    private String password;
    
    @Column(columnDefinition = "tinyint", nullable = false)
    private byte type;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registrationTime;

    @Column(name = "image_url", nullable = true)
    private String imageURL;

    @Column(columnDefinition = "varchar(20) COMMENT 'may not only be male or female'", nullable = true)
    private String gender;

    @Column(columnDefinition = "varchar(11)", nullable = true)
    private String phoneNumber;

    @Column(columnDefinition = "varchar(100)", nullable = true)
    private String email;

    
}
