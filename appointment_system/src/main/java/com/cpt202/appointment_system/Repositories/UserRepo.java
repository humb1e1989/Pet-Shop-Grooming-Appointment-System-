package com.cpt202.appointment_system.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202.appointment_system.Models.User;


public interface UserRepo extends JpaRepository<User, Integer>{

    public List<User> findByType(byte type);
    
    public List<User> findByUsernameContaining(String username);
     
    public User findByUid(int uid);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    public void save(User user);

    public Optional<User> findByUsername(String username);
}
