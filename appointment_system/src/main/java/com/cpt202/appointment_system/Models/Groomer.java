package com.cpt202.appointment_system.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Groomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "int(7)")
    private int gid;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String gender;

    @Column(name = "image_url", nullable = false)
    private String imageURL;

    // name it "ranking" in MySQL to avoid possible error 
    // since rank() is a buil-in function in MySQL 
    @Column(name = "ranking", columnDefinition = "tinyint", nullable = false)
    private byte rank;

    @Column(columnDefinition = "varchar(11)", nullable = false)
    private String phoneNumber;

    
    // I think "description" is a reasonable attribute but not a basic one.
    // Besides, I am not familiar with storing large text in MySQL for a moment. 

    // @Column(columnDefinition = " ", nullable = false)
    // private String description;
    

    public Groomer() {
    }

    
    public Groomer(int gid, String name, String gender, String imageURL, byte rank, String phoneNumber) {
        this.gid = gid;
        this.name = name;
        this.gender = gender;
        this.imageURL = imageURL;
        this.rank = rank;
        this.phoneNumber = phoneNumber;
    }    
    
}
