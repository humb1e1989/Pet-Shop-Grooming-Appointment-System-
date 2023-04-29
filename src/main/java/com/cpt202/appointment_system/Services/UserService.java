package com.cpt202.appointment_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.AccessOptions.GetOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Appointment;
import com.cpt202.appointment_system.Models.Groomer;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Common.UserTool;
import com.cpt202.appointment_system.Repositories.AppointmentRepo;
import com.cpt202.appointment_system.Repositories.GroomerRepo;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Utils.FileUploadUtil;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private GroomerRepo groomerRepo;

    // CYZ
    public List<User> listAllCustomers_M() {
        return userRepo.findAll();
    }

    // CYZ
    // I think it might have a better way to do it.
    // However, right now I do not know how to join many tables using Jpa.
    public Result<?> viewOneCustomer_M(Integer uid) {
        User user = userRepo.findByUid(uid);

        if (user != null) {
            List<Appointment> al = appointmentRepo.findByUser(user);
            List<Pet> pl = petRepo.findByUser(user);
            UserTool ut = new UserTool(user, al, pl);
            return Result.success(ut, "Find All Info About This Customer!");
        }

        return Result.error("-1", "Customer Doesn't Exist.");

    }

    // CYZ
    public List<User> searchCustomerByName_M(String username) {
        return userRepo.findByUsernameContaining(username);
    }

    
    // ZYH PBI NO.i Customer can view only his appointments' details
    public Result<?> getAppointmentDetail_C(@RequestParam Appointment appointment, User user) {
        List<Appointment> appointment1 = appointmentRepo.findByUser(user);
        if(appointment1 != null) return Result.success(appointment1, "Find Matching Appointment!");
        return Result.error("-1", "No Matching Appointment Found.");
    }

    
    // ZYH PBI NO.ii Customer can search a groomer by name
    public Result<?> searchGroomerByName_C(String name) {
        List<Groomer> groomerList = groomerRepo.findByNameContaining(name);
        if(!groomerList.isEmpty()) return Result.success(groomerList, "Find Matching Groomer!");
        return Result.error("-1", "No Matching Groomer Found.");
    }


    // ZYH PBI NO.iii Customer can view all his pets
    public Result<?> listAllPets_C(Integer uid) {
        User user = userRepo.findByUid(uid);
        List<Pet> petList = petRepo.findByUser(user);
        if(!petList.isEmpty()) return Result.success(petList, "Find Matching Pet!");
        return Result.error("-1", "No Matching Pet Found.");
    }

    // ZYH PBI NO.iv Customer can view his profile
    public Result<?> viewProfile_C(Integer uid) {
        User user = userRepo.findByUid(uid);
        if(user != null) return Result.success(user, "Here is your profile!");
        return Result.error("-1", "No Matching User Found.");
    }

    // ZYH PBI NO.v Customer can edit his profile
    // This method roughly overwrites the original object 
    // by creating a new user object. 
    // Optimization may be required.
    public Result<?> editProfile_C(User user) {
        User user1 = userRepo.findByUid(user.getUid());
        if(user1 != null) {
            user1.setUsername(user.getUsername());
            user1.setPassword(user.getPassword());
            user1.setPhoneNumber(user.getPhoneNumber());
            user1.setEmail(user.getEmail());
            user1.setImageURL(user.getImageURL());
            user1.setGender(user.getGender());
            userRepo.save(user1);
            return Result.success(user1, "Edit Successfully!");
        }
        return Result.error("-1", "No Matching User Found.");
    }

    public void updateUser(User user) {
        userRepo.save(user);
    }

    public void deleteUserById(Integer uid) {
        userRepo.deleteByUid(uid);
    }


    public int editProfile_C(MultipartFile file, User user) {

        Integer uid = user.getUid();
        String uidString= Integer.toString(uid);
        String imagePath = FileUploadUtil.userPicture(file, uidString);

        // no image or empty image
        if (imagePath.equals("0") || imagePath.equals("-1")){
            User u = userRepo.findByUid(uid);
            user.setImageURL(u.getImageURL());
            userRepo.save(user);
            return 0;
        }

        // unsupported image format 
        if (imagePath.equals("-2")){
            return 2;
        }
       
        // image storage error
        if (imagePath.equals("-3")){
            return 3;
        }
        
        user.setImageURL(imagePath);
        userRepo.save(user);
        return 0;

    }

}
