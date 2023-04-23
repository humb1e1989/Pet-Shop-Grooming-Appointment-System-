// package com.cpt202.appointment_system.Services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

<<<<<<< Updated upstream
import com.cpt202.appointment_system.Repositories.UserRepo;

// I think LoginService can be a part of Userservice
@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;

}
=======
// import com.cpt202.appointment_system.Repositories.UserRepo;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import com.cpt202.appointment_system.Models.User;
// import com.cpt202.appointment_system.Repositories.UserRepo;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import javax.servlet.http.HttpSession;

// import java.sql.Timestamp;
// import java.util.Optional;

// @Service
// public class LoginService {
    
//     @Autowired
//     private UserRepo userRepository;


//     public int loginUser(String username,String password) {
//         Optional<User> dbUser = userRepository.findByUsername(username);
//         // if (dbUser.isPresent()&&dbUser.get().getPassword().equals(new BCryptPasswordEncoder().encode(password))){
//             if (dbUser.isPresent()&&dbUser.get().getPassword().equals(password)){
//                 if(dbUser.get().getType().equals(0)){
//                     return 0;
//                 }
//                 else{
//                     return 1;
//                 }
//             }
            
//             else {return 2;}
            
           
//     }

//     public int registerUser(User user) {
//         if (userRepository.existsByUsername(user.getUsername())) {
//             return 1;
//         } // 测试用户名被注册没有

//         if ( userRepository.existsByEmail(user.getEmail())) {
//             return 2;
//         } 
//         else{
//             Timestamp currentTime = new Timestamp(System.currentTimeMillis());
//             user.setPassword(user.getPassword());
//             user.setRegistrationTime(currentTime);
//             userRepository.save(user);
//             return 3;
//         }
//     }


//     public ResponseEntity<String> logoutUser(HttpSession session) {
//         session.invalidate();
//         return new ResponseEntity<>("User logged out successfully.", HttpStatus.OK);
//     }


// //前端检查
//     public boolean checkUnique(String username) {
//         Optional<User> user = userRepository.findByUsername(username);
//         return user.isEmpty();
//     }

//     public boolean checkUniqueEmail(String email) {
//         Optional<User> user = userRepository.findByEmail(email);
//         return user.isEmpty();
//     }
// }
>>>>>>> Stashed changes
