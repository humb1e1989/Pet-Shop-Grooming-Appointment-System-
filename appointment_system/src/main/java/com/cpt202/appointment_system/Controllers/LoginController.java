package com.cpt202.appointment_system.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/appointment-system")
public class LoginController {

    @Autowired
    private UserRepo userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Username or email already exists.", HttpStatus.BAD_REQUEST);
        } // the test if the username and the email havr already be registered before.
        else{
            userRepository.save(user);
        return new ResponseEntity<>("User registered successfully.", HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user, HttpSession session) {
        Optional<User> dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser.isPresent()){
            if(dbUser.get().getPassword().equals(user.getPassword())){
                dbUser.get().setFailedLoginAttempts(0);
                userRepository.save(dbUser.get());
                session.setAttribute("userId", dbUser.get().getId());
                return new ResponseEntity<>("User logged in successfully.", HttpStatus.OK);
            } // successful logging in
            else{
                int failedAttempts = dbUser.get().getFailedLoginAttempts();
                if (failedAttempts >= 3) {
                    // Trigger password retrieval process
                    return new ResponseEntity<>("You have entered the wrong password 3 times. Please retrieve your password.", HttpStatus.BAD_REQUEST);
                } else {
                    dbUser.get().setFailedLoginAttempts(failedAttempts + 1);
                    userRepository.save(dbUser.get());
                    return new ResponseEntity<>("Invalid username or password.", HttpStatus.BAD_REQUEST);
                }
            }
        } return new ResponseEntity<>("Invalid username or password.", HttpStatus.BAD_REQUEST);
        
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("User logged out successfully.", HttpStatus.OK);
    }
    
}
