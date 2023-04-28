package com.cpt202.appointment_system.Repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cpt202.appointment_system.Models.Appointment;

// import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    public List<User> findByType(Integer type);

    public List<User> findByUsernameContaining(String username);

    public User findByUid(Integer uid);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findByUsernameOptional(String username);


    public User findByUsername(String username);

    public List<Appointment> findByUidIs(Integer uid);
    // return the appointment list with a specific username
    // public List<Appointment> findByUidIs(Integer uid);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `user` WHERE uid = ?1", nativeQuery = true)
    public void deleteByUid(Integer uid);
}











// package com.cpt202.appointment_system.Repositories;

// import java.util.List;
// import java.util.Optional;
// import org.springframework.data.jpa.repository.JpaRepository;
// import com.cpt202.appointment_system.Models.Appointment;
// import com.cpt202.appointment_system.Models.User;

// public interface UserRepo extends JpaRepository<User, Integer> {

//     public List<User> findByType(Integer type);

//     public List<User> findByUsernameContaining(String username);

//     public User findByUid(Integer uid);

//     public boolean existsByUsername(String username);

//     public boolean existsByEmail(String email);

//     public Optional<User> findByUsername(String username);

//     public Optional<User> findByEmail(String email);

//     // return the appointment list with a specific username
//     public List<Appointment> findByUidIs(Integer uid);

//     public User getbyusername(String username);
// }
