package com.cpt202.appointment_system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt202.appointment_system.Repositories.UserRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class LoginService {
    
    @Autowired
    private UserRepo userRepository;


    
    // public ResponseEntity<String> registerUser(User user) {
    //     if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
    //         return new ResponseEntity<>("Username or email already exists.", HttpStatus.BAD_REQUEST);
    //     } // the test if the username and the email havr already be registered before.
    //     else{
    //         Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    //         user.setRegistrationTime(currentTime);
    //         userRepository.save(user);
    //     return new ResponseEntity<>("User registered successfully.", HttpStatus.OK);
    //     }
    // }


    // public ResponseEntity<String> loginUser(User user, HttpSession session) {
    //     Optional<User> dbUser = userRepository.findByUsername(user.getUsername());
    //     if (dbUser.isPresent()){
    //         if(dbUser.get().getPassword().equals(user.getPassword())){
    //             dbUser.get().setFailedLoginAttempts(0);
    //             userRepository.save(dbUser.get());
    //             session.setAttribute("userId", dbUser.get().getUid());
    //             return new ResponseEntity<>("User logged in successfully.", HttpStatus.OK);
    //         } // successful logging in
    //         else{
    //             int failedAttempts = dbUser.get().getFailedLoginAttempts();
    //             if (failedAttempts >= 3) {
    //                 // Trigger password retrieval process
    //                 return new ResponseEntity<>("You have entered the wrong password 3 times. Please retrieve your password.", HttpStatus.BAD_REQUEST);
    //             } else {
    //                 dbUser.get().setFailedLoginAttempts(failedAttempts + 1);
    //                 userRepository.save(dbUser.get());
    //                 return new ResponseEntity<>("Invalid username or password.", HttpStatus.BAD_REQUEST);
    //             }
    //         }
    //     } return new ResponseEntity<>("Invalid username or password.", HttpStatus.BAD_REQUEST);
        
    // }

    // public boolean loginUser(String username,String password) {
    //     Optional<User> dbUser = userRepository.findByUsername(username);
    //     if (dbUser.isPresent()&&dbUser.get().getPassword().equals(password)){
    //         user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    // public int registerUser(User user) {
    //     if (userRepository.existsByUsername(user.getUsername())) {
    //         return 1;
    //     } // 测试用户名被注册没有

    //     if ( userRepository.existsByEmail(user.getEmail())) {
    //         return 2;
    //     } // 测试邮箱被注册没有

    //     // if ( userRepository.existsByEmail(user.getEmail())) {
    //     //     return 3;
    //     // } // 测试邮箱验证

    //     else{
    //         Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    //         user.setRegistrationTime(currentTime);
    //         user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    //         userRepository.save(user);
    //         return 3;
    //     }
    // }


    // public ResponseEntity<String> logoutUser(HttpSession session) {
    //     session.invalidate();
    //     return new ResponseEntity<>("User logged out successfully.", HttpStatus.OK);
    // }
}
