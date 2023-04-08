package com.cpt202.appointment_system.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pid;

    @Column(nullable = false)
    private int uId;

    @Column(columnDefinition = "varchar(10)", nullable = false)
    private String size;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String type;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;

    @Column(name = "image_url", nullable = true)
    private String imageURL;


    
}
