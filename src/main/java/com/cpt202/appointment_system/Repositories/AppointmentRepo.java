package com.cpt202.appointment_system.Repositories;

import java.sql.Timestamp;
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
    // ZYH TODO: I do not realy know how to form the function name (if any problem,
    // please change it)
    public List<Appointment> findByUserUsernameContaining(String username);

    public List<Appointment> findByUserContaining(String username);

    public Appointment findByAid(Integer aid);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE gid = ?1", nativeQuery = true)
    public List<Appointment> findByGroomer(Integer gid);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE pid = ?1", nativeQuery = true)
    public List<Appointment> findByPet(Integer pid);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE sid = ?1", nativeQuery = true)
    public List<Appointment> findBySid(Integer sid);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE total_price = ?1", nativeQuery = true)
    public List<Appointment> findBytotalPrice(Double price);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE gid = ?1", nativeQuery = true)
    public List<Appointment> findByGid(Integer gid);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE status = :status", nativeQuery = true)
    public List<Appointment> findByStatus(@Param("status") String status);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE pid = ?1", nativeQuery = true)
    public List<Appointment> findByPid(Integer pid);

    @Transactional(timeout = 10)
    @Query(value = "SELECT * FROM appointment WHERE uid = ?1", nativeQuery = true)
    public List<Appointment> findByUid(Integer uid);

    // @Transactional(timeout = 10)
    // @Query(value = "SELECT * FROM appointment WHERE service_name = :serviceType", nativeQuery = true)
    // public List<Appointment> findByService_type(@Param("serviceType") String serviceType);

    // @Transactional(timeout = 10)
    // @Query(value = "SELECT * FROM appointment WHERE service_name = :service_type", nativeQuery = true)
    // public List<Appointment> fetchByServiceType(@Param("service_type") String service_type);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment SET `status` = ?2 WHERE aid = ?1", nativeQuery = true)
    public void updateStatusByAid(@Param("aid") Integer aid, @Param("status") String status);

    // WJT
    public List<Appointment> findByserviceType(String servicetype);

    public List<Appointment> findByGroomer(Groomer groomer);

    @Query(value = "SELECT * FROM appointment WHERE finish_time BETWEEN ?1 AND ?2", nativeQuery = true)
    public List<Appointment> findAllByFinishTimeBetween(Timestamp start, Timestamp end);
    
    @Query(value = "SELECT YEAR(finish_time), COUNT(*) FROM appointment GROUP BY YEAR(finish_time)", nativeQuery = true)
    public List<Integer[]> findYearAndCount();

    // 使用@Query注解来指定自定义的JPQL语句，根据年份来查询sale
    @Query(value = "select * from appointment where YEAR(finish_time) = :year", nativeQuery = true)
    public List<Appointment> findByYear(@Param("year") int year);

}
