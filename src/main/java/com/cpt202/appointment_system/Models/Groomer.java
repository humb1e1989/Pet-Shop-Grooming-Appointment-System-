package com.cpt202.appointment_system.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;

import lombok.Data;


@Data
@Entity
public class Groomer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(7)")
    private Integer gid;
    
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String gender;

    @Column(columnDefinition = "varchar(255) default '/assets/images/no-user.png'", name = "image_url", nullable = true)
    private String imageURL;

    // name it "ranking" in MySQL to avoid possible error 
    // since rank() is a buil-in function in MySQL 
    // need
    @Column(name = "ranking", columnDefinition = "tinyint", nullable = false)
    private Integer rank;

    @Column(columnDefinition = "varchar(11)", nullable = true)
    private String phoneNumber;

    @Column(columnDefinition = "varchar(255) default 'No Description'", nullable = true)
    private String description;
    
    public Groomer() {
    }


    public Groomer(int gid) {
        this.gid=gid;
    }


    
    public Groomer(int gid, String name, String gender, String imageURL, Integer rank, String phoneNumber) {
        this.gid = gid;
        this.name = name;
        this.gender = gender;
        this.imageURL = imageURL;
        this.rank = rank;
        this.phoneNumber = phoneNumber;
    }    
    
}
