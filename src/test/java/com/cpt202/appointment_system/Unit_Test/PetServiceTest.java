package com.cpt202.appointment_system.Unit_Test;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cpt202.appointment_system.Models.Pet;
import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.PetRepo;
import com.cpt202.appointment_system.Services.PetService;

import com.cpt202.appointment_system.Utils.FileUploadUtil;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class PetServiceTest {

    @Mock
    private PetRepo petRepo;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAllPets() {
        // Given
        User user = new User();
        user.setUid(1);
        user.setEmail("test@test.com");
        user.setPassword("password");

        Pet pet1 = new Pet();
        pet1.setPid(1);
        pet1.setName("Pet1");
        pet1.setUser(user);

        Pet pet2 = new Pet();
        pet2.setPid(2);
        pet2.setName("Pet2");
        pet2.setUser(user);

        List<Pet> pets = new ArrayList<Pet>();
        pets.add(pet1);
        pets.add(pet2);

        when(petRepo.findByUser(user)).thenReturn(pets);

        // When
        List<Pet> result = petService.listAllPets(user);

        // Then
        Assertions.assertEquals(pets.size(), result.size());
        Assertions.assertEquals(pets.get(0).getName(), result.get(0).getName());
        Assertions.assertEquals(pets.get(1).getName(), result.get(1).getName());
    }



    // @Test
    // public void testAddPetSuccess() {
    //     // prepare test data
    //     User user = new User();
    //     user.setUid(1);
    //     Pet pet = new Pet();
    //     pet.setName("Fluffy");
    //     pet.setUser(user);
    //     MultipartFile file = new MockMultipartFile("test.jpg", new byte[0]);

    //     // stub methods
    //     when(petRepo.save(pet)).thenReturn(pet);
    //     when(FileUploadUtil.petPicture(file, "1")).thenReturn("/path/to/image.jpg");

    //     // invoke method
    //     int result = petService.addPet(file, pet);

    //     // verify behavior and assertions
    //     verify(petRepo, times(1)).save(pet);
    //     assertEquals(0, result);
    //     assertEquals("/path/to/image.jpg", pet.getImageURL());
    // }

    // @Test
    // public void testAddPetNoImage() {
    //     // prepare test data
    //     User user = new User();
    //     user.setUid(1);
    //     Pet pet = new Pet();
    //     pet.setName("Fluffy");
    //     pet.setUser(user);
    //     MultipartFile file = new MockMultipartFile("test.jpg", new byte[0]);

    //     // stub methods
    //     when(petRepo.save(pet)).thenReturn(pet);
    //     when(FileUploadUtil.petPicture(file, "1")).thenReturn("0");

    //     // invoke method
    //     int result = petService.addPet(file, pet);

    //     // verify behavior and assertions
    //     verify(petRepo, times(1)).save(pet);
    //     assertEquals(0, result);
    //     assertEquals(null, pet.getImageURL());
    // }

    // @Test
    // public void testAddPetUnsupportedImage() {
    //     // prepare test data
    //     User user = new User();
    //     user.setUid(1);
    //     Pet pet = new Pet();
    //     pet.setName("Fluffy");
    //     pet.setUser(user);
    //     MultipartFile file = new MockMultipartFile("test.pdf", new byte[0]);

    //     // stub methods
    //     when(petRepo.save(pet)).thenReturn(pet);
    //     when(FileUploadUtil.petPicture(file, "1")).thenReturn("-2");

    //     // invoke method
    //     int result = petService.addPet(file, pet);

    //     // verify behavior and assertions
    //     verify(petRepo, never()).save(pet);
    //     assertEquals(2, result);
    //     assertEquals(null, pet.getImageURL());
    // }

}