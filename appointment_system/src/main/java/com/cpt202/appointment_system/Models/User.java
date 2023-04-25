package com.cpt202.appointment_system.Models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.format.annotation.DateTimeFormat;
import org.yaml.snakeyaml.events.Event.ID;

import lombok.Data;


@Data
@Entity
public class User {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;

    @Column(columnDefinition = "varchar(50)", nullable = false, unique = true)
    private String username;

    @Column(columnDefinition = "varchar(15)", nullable = false)
    private String password;
    
    @Column(columnDefinition = "tinyint", nullable = false)
    private Integer type;  // type 0: customer; type 1: manager

    @Column(columnDefinition = "Timestamp DEFAULT CURRENT_TIMESTAMP", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp registrationTime;

    @Column(name = "image_url", nullable = true)
    private String imageURL;

    @Column(columnDefinition = "varchar(20) COMMENT 'may not only be male or female'", nullable = true)
    private String gender;

    @Column(columnDefinition = "varchar(11)", nullable = true)
    private String phoneNumber;

    @Column(columnDefinition = "varchar(100)", nullable = true)
    private String email;

    //TODO : default value = 0
    @Column(nullable = false, columnDefinition = "int(11) default 0")
    private Integer failedLoginAttempts = 0;
    


    public User(int uid) {
        this.uid=uid;
    }
    

    public User() {
    }


    public User(Integer uid, String username, String password, Integer type, Timestamp registrationTime,
            String imageURL, String gender, String phoneNumber, String email, Integer failedLoginAttempts) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.type = type;
        this.registrationTime = registrationTime;
        this.imageURL = imageURL;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.failedLoginAttempts = failedLoginAttempts;
    }






      
}
