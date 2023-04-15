package com.cpt202.appointment_system.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    public List<User> findByType(Integer type);

    public List<User> findByUsernameContaining(String username);

    public User findByUid(Integer uid);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    public Optional<User> findByUsername(String username);

    // return the appointment list with a specific username
    public List<Appointment> findByUidIs(Integer uid);
}
