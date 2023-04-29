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
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(7)")
    private Integer sid;

    @Column(columnDefinition = "varchar(50)", nullable = true)
    private String serviceName;
    
    @Column(columnDefinition = "int(7)", nullable = true)
    private Integer serviceTime;

    @Column(columnDefinition = "int(7)", nullable = true)
    private Integer basicPrice;

    public ServiceType(String serviceName, int serviceTime, int basicPrice) {
        serviceName = serviceName;
        serviceTime = serviceTime;
        basicPrice = basicPrice;
    }

    public ServiceType() {
    }

    

}
