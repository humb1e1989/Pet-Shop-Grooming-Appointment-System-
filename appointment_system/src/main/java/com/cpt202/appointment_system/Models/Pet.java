package com.cpt202.appointment_system.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Pet {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int userId;

    private String size;

    private String type;

    private String name;

    @Column(name = "image_url")
    private String imageURL;


    
}
