package com.cpt202.appointment_system.Models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Appointment {
    
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date time;

    private Date finishTime;

    private Date cancelTime;

    private byte status;

    private int groomerId;

    private int userId;

    private String serviceType;


 




}
