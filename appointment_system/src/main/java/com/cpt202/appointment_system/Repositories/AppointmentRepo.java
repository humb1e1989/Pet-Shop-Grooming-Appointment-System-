package com.cpt202.appointment_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.User;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

    public List<Appointment> findByUser(User user);

    // Appointment can be searched by username
    //ZYH TODO: I do not realy know how to form the function name (if any problem, please change it)
    public List<Appointment> findByUserContaining(String username);

    public Appointment findByAid(Integer aid);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE gid = ?1", nativeQuery = true)
    public List<Appointment> findByGroomer(Integer gid);

    // @Transactional(timeout = 10)
    // @Query(value = "SELECT * FROM appointment WHERE name = :gname", nativeQuery = true)
    // public List<Appointment> findByGname(@Param("gname") String gname);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE pid = ?1", nativeQuery = true)
    public List<Appointment> findByPet(Integer pid);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE sid = ?1", nativeQuery = true)
    public List<Appointment> findBySid(Integer sid);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE total_price = ?1", nativeQuery = true)
    public List<Appointment> findByTotal_price(Double price);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE service_type = :service_type", nativeQuery = true)
    public List<Appointment> findByService_type(@Param("service_type") String service_type);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE status = :status", nativeQuery = true)
    public List<Appointment> findByStatus(@Param("status") String status);


    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment SET `status` = ?1 WHERE aid = ?2", nativeQuery = true)
    public void updateStatusByAid(@Param("status") String status, @Param("aid") Integer aid);

    // // WJT
    // public List<Appointment> findByService_type(String servicetype);

    public List<Appointment> findByGroomer(Groomer groomer);

}
