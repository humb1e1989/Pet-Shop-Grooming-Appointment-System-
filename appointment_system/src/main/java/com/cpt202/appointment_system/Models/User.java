package com.cpt202.appointment_system.Models;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.yaml.snakeyaml.events.Event.ID;

import lombok.Data;


@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uid;

    @Column(columnDefinition = "varchar(50)", nullable = false, unique = true)
    private String username;

    @Column(columnDefinition = "varchar(15)", nullable = false)
    private String password;
    
    @Column(columnDefinition = "tinyint", nullable = false)
    private byte type;  // type 0: customer; type 1: manager

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

    @Column(nullable = false)
    private int failedLoginAttempts = 0;
    


    public User() {
    }


    public User(int uid, String username, String password, byte type, Date registrationTime, String imageURL,
            String gender, String phoneNumber, String email) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.type = type;
        this.registrationTime = registrationTime;
        this.imageURL = imageURL;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }


    public int getId() {
        return uid;
    }
      // Getters
      public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }
    // Setters
    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

}
