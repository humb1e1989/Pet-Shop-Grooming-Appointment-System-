package com.cpt202.appointment_system.Models;
import java.sql.Date;
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
    private Integer aid;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")//bowen li's modification
    private Date createTime;

    @Column(columnDefinition = "DATETIME", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")//bowen li's modification
    private Date startTime;

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

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String petName;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String serviceType;

    @ManyToOne
    @JoinColumn(name = "gid", referencedColumnName = "gid", nullable = false)
    private Groomer groomer;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
    private User user;

    @ManyToOne//bowenli's modification
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)
    private Pet pet;

    @Column(columnDefinition = "DATETIME", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")//bowen li's modification
    private Date finishTime;
    
    @Column(columnDefinition = "DATETIME", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")//bowen li's modification
    private Date cancelTime;

    //bowen li's modification
    @Column(columnDefinition = "Double", nullable = true)
    private Double totalprice;
    
    public Appointment(){
        
    }

}
