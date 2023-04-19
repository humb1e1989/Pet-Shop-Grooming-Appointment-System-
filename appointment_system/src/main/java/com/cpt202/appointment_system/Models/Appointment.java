package com.cpt202.appointment_system.Models;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Entity
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "int(7)")
    private Integer aid;

    @Column(columnDefinition = "Timestamp DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")//bowen li's modification
    private Timestamp createTime;

    @Column(columnDefinition = "Timestamp", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")//bowen li's modification
    private Timestamp startTime;

    /* @Column(columnDefinition = "tinyint", nullable = false)
        ZYH's modification: change the type of status from tinyint to varchar(50)
        The status of an appointment may be something like "pending", "accepted", "finished", "cancelled":
            When an appointment is created, the status is "pending"
            When an appointment is accepted, the status is "accepted"
            When an appointment is finished, the status is "finished"
            When an appointment is cancelled, the status is "cancelled"
    */

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String status;

    //Bowen Li's modification
    //Actually create a new constructor can slove this problem I add a pet Object below
    //you can try to use pid to modify it
    // @Column(columnDefinition = "varchar(50)", nullable = false)
    // private String petName;
    
    @ManyToOne
    @JoinColumn(name="sid",referencedColumnName = "sid",nullable = false)
    private ServiceType serviceType;

    @ManyToOne
    @JoinColumn(name = "gid", referencedColumnName = "gid", nullable = false)
    private Groomer groomer;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
    private User user;

    @ManyToOne//bowenli's modification
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)
    private Pet pet;

    @Column(columnDefinition = "Timestamp", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")//bowen li's modification
    private Timestamp finishTime;
    
    @Column(columnDefinition = "Timestamp", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")//bowen li's modification
    private Timestamp cancelTime;

    //bowen li's modification
    @Column(columnDefinition = "Double", nullable = true)
    private Double totalprice;
    
    public Appointment(){
        
    }


    public Appointment(Integer aid, Timestamp createTime, Timestamp startTime, String status, ServiceType serviceType,
            Groomer groomer, User user, Pet pet, Timestamp finishTime, Timestamp cancelTime, Double totalprice) {
        this.aid = aid;
        this.createTime = createTime;
        this.startTime = startTime;
        this.status = status;
        this.serviceType = serviceType;
        this.groomer = groomer;
        this.user = user;
        this.pet = pet;
        this.finishTime = finishTime;
        this.cancelTime = cancelTime;
        this.totalprice = totalprice;
    }



    public Appointment(Timestamp startTime, ServiceType serviceType, Groomer groomer, User user, Pet pet) {
        this.startTime = startTime;
        this.serviceType = serviceType;
        this.groomer = groomer;
        this.user = user;
        this.pet = pet;
    }
    

}
