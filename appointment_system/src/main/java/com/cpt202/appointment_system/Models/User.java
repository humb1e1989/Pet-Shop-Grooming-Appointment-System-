package com.cpt202.appointment_system.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String password;
    
    private String phoneNumber;
    
    private String email;
    
    private byte type;

    @Column(name = "image_url")
    private String imageURL;

    private Date registrationTime;

 

}
