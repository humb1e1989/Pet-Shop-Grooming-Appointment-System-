package com.cpt202.appointment_system.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pid;

    @Column(columnDefinition = "varchar(10)", nullable = false)
    private String size;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String type;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
    private User user;

    @Column(name = "image_url", nullable = true)
    private String imageURL;

    public Pet(Integer pid) {
        this.pid = pid;
    }
    
}
