package com.cpt202.appointment_system.Models;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentForm {
    

    private Integer aid;

    private Timestamp createTime;


    private String startTime;

    /* @Column(columnDefinition = "tinyint", nullable = false)
        ZYH's modification: change the type of status from tinyint to varchar(50)
        The status of an appointment may be something like "pending", "accepted", "finished", "cancelled":
            When an appointment is created, the status is "pending"
            When an appointment is accepted, the status is "accepted"
            When an appointment is finished, the status is "finished"
            When an appointment is cancelled, the status is "cancelled"
    */

    private String status;

    //Bowen Li's modification
    //Actually create a new constructor can slove this problem I add a pet Object below
    //you can try to use pid to modify it
    // @Column(columnDefinition = "varchar(50)", nullable = false)
    // private String petName;
    
    private ServiceType serviceType;

    private Groomer groomer;

    private User user;

    private Pet pet;

    private Timestamp finishTime;
    
    private Timestamp cancelTime;

    private Double totalprice;


    public AppointmentForm(String startTime, ServiceType serviceType, Groomer groomer, User user, Pet pet) {
        this.startTime = startTime;
        this.serviceType = serviceType;
        this.groomer = groomer;
        this.user = user;
        this.pet = pet;
    }



    public Appointment toAppointment(Class<Appointment> clazz){
        Appointment appointment = BeanUtils.instantiateClass(clazz);
        if (StringUtils.hasLength(startTime)) {
            LocalDateTime localDateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            appointment.setStartTime(Timestamp.valueOf(localDateTime));
        }
        BeanUtils.copyProperties(this, clazz);
        return appointment;
    
    }



}
