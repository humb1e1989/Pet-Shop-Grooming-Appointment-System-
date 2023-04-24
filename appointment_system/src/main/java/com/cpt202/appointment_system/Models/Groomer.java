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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gid;
    
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String gender;

    @Column(columnDefinition = "varchar(255) default '/assets/images/no-user.png'", name = "image_url", nullable = true)
    private String image_url;

    // @Column(columnDefinition = "varchar(255)", name = "imageURL", nullable = true)
    // private String imageURL;

    // name it "ranking" in MySQL to avoid possible error 
    // since rank() is a buil-in function in MySQL 
    // need
    @Column(name = "ranking", columnDefinition = "int", nullable = false)
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


    
    public Groomer(Integer gid, String name, String gender, String image_url, Integer rank, String phoneNumber) {
        this.gid = gid;
        this.name = name;
        this.gender = gender;
        this.image_url = image_url;
        this.rank = rank;
        this.phoneNumber = phoneNumber;
    }    
    
}
