package com.cpt202.appointment_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cpt202.appointment_system.Common.Result;
import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Utils.FileUploadUtil;

@Service
public class PetService {

    @Autowired
    private PetRepo petRepo;


    // below are all the things needed by personal page
    public List<Pet> listAllPets(User user) {
        List<Pet> petList = petRepo.findByUser(user);
        return petList;
    }

    public int addPet(MultipartFile file, Pet pet) {
    
        Integer uid = pet.getUser().getUid();
        String uidString = Integer.toString(uid);
        String imagePath = FileUploadUtil.petPicture(file, uidString);

        // no image or empty image
        if (imagePath.equals("0") || imagePath.equals("-1")){
            petRepo.save(pet);
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
        
        pet.setImageURL(imagePath);
        petRepo.save(pet);
        return 0;
        
    }


    public int editPet(MultipartFile file, Pet pet){

        int pid = pet.getPid();
        Pet originPet = petRepo.findByPid(pid);
        String originType = originPet.getType();
        String originSize = originPet.getSize();
        String originName = originPet.getName();
        String originImage = originPet.getImageURL();

        if (pet.getType().equals("")){
            pet.setType(originType);
        }

        if (pet.getSize().equals("")){
            pet.setSize(originSize);
        }

        if (pet.getName().trim().equals("")){
            pet.setName(originName);
        }

        
        Integer uid = pet.getUser().getUid();
        String uidString = Integer.toString(uid);
        String imagePath = FileUploadUtil.petPicture(file, uidString);

        // no image or empty image
        if (imagePath.equals("0") || imagePath.equals("-1")){
            pet.setImageURL(originImage);
            petRepo.save(pet);
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
        
        pet.setImageURL(imagePath);
        petRepo.save(pet);
        return 0;

    }


    public void pseudoDeletePet(Pet pet, User user) {

        // FileUploadUtil.deletePetPic(pet);     
           
        pet.setImageURL(null);

        pet.setName(pet.getName() + " (Deleted)");
        pet.setSize("");
        pet.setType("");
        pet.setUser(user);
        petRepo.save(pet); 
    }

}
