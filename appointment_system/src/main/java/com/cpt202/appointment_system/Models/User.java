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


    public int getUid() {
        return uid;
    }


    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public byte getType() {
        return type;
    }


    public void setType(byte type) {
        this.type = type;
    }


    public Date getRegistrationTime() {
        return registrationTime;
    }


    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }


    public String getImageURL() {
        return imageURL;
    }


    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    

 


    
}
