package com.cpt202.appointment_system.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.cpt202.appointment_system.Models.Pet;


public class FileUploadUtil {

    
    // private static final String USERPATH = "C:\\Users\\Administrator\\User_images\\";
    // private static final String GROOMERPATH = "C:\\Users\\Administrator\\Groomer_images\\";
    private static final String USERPATH = "C:\\Users\\86138\\Desktop\\User_images\\";
    private static final String GROOMERPATH = "C:\\Users\\86138\\Desktop\\Groomer_images\\";
    private static final String PETPATH = "\\Pet_images\\";

    private static final String GROOMERPATH_URL_STRING = "/g-images/";
    private static final String USERPATH_URL_STRING = "/u-images/";

    private static final String TYPE_JPEG = "jpeg";
    private static final String TYPE_JPG = "jpg";
    private static final String TYPE_JPE = "jpe";
    private static final String TYPE_JFIF = "jfif";
    private static final String TYPE_JIF = "jif";
    private static final String TYPE_PNG = "png";


    public static String groomerPicture(MultipartFile file) {

        // check the file
        if (file == null){
            return "0";
        }

        if (file.isEmpty()) {
            return "-1";
        }

        if (!isCorrectFormat(file)){
            return "-2";
        }


        File directory = new File(GROOMERPATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // rename the file
        String originalFilename = file.getOriginalFilename();
        String fileExtention= originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtention;

        try {
            Path path = Paths.get(GROOMERPATH + uniqueFileName);
            Files.write(path, file.getBytes());
            String imageURL = GROOMERPATH_URL_STRING + uniqueFileName;
            return imageURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "-3";
        }

        
    }



    public static String userPicture(MultipartFile file, String uid){

        if (file == null){
            return "0";
        }

        if (file.isEmpty()) {
            return "-1";
        }

        if (!isCorrectFormat(file)){
            return "-2";
        }


        File directory = new File(USERPATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            // obtain the suffix of picture
            String originalFilename = file.getOriginalFilename();
            String fileExtention= originalFilename.substring(originalFilename.lastIndexOf("."));

            // generate unique picture name
            String uniqueFileName = UUID.randomUUID().toString() + fileExtention;

            // create directory if not exists
            String path = USERPATH + uid + "\\";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // store the picture
            Path filePath = Paths.get(path + uniqueFileName);
            Files.write(filePath, file.getBytes());
            String imageURL = USERPATH_URL_STRING + uid + "/" + uniqueFileName;

            return imageURL;
        } 
        catch (IOException e) {
            return "-3";
        }

    }


    public static String petPicture(MultipartFile file, String uid){

        if (file == null){
            return "0";
        }

        if (file.isEmpty()) {
            return "-1";
        }

        if (!isCorrectFormat(file)){
            return "-2";
        }


        File directory = new File(USERPATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            // obtain the suffix of picture
            String originalFilename = file.getOriginalFilename();
            String fileExtention= originalFilename.substring(originalFilename.lastIndexOf("."));

            // generate unique picture name
            String uniqueFileName = UUID.randomUUID().toString() + fileExtention;

            // create directory if not exists
            String path = USERPATH + uid + PETPATH;
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // store the picture
            Path filePath = Paths.get(path + uniqueFileName);
            Files.write(filePath, file.getBytes());
            String imageURL = USERPATH_URL_STRING + uid + "/Pet_images/" + uniqueFileName;

            return imageURL;
        } 
        catch (IOException e) {
            return "-3";
        }


    }

    public static boolean isCorrectFormat(MultipartFile file){

        String fileName = file.getOriginalFilename();

        if (fileName.lastIndexOf(".") == -1){
            return false;
        }

        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (suffix.equalsIgnoreCase(TYPE_JPG) || suffix.equalsIgnoreCase(TYPE_PNG) || 
        suffix.equalsIgnoreCase(TYPE_JPEG) || suffix.equalsIgnoreCase(TYPE_JFIF) ||
        suffix.equalsIgnoreCase(TYPE_JIF) || suffix.equalsIgnoreCase(TYPE_JPE)){
            return true;
        }

        return false;
    }


    // public static int deletePetPic(Pet pet){

    //     String imageURL = pet.getImageURL();

    //     if (imageURL != null || !imageURL.equals("")){
           
    //         String partialPath = imageURL.substring(10).replace("/", "\\");
    //         String absolutePath = USERPATH + partialPath;

    //         File file = new File(absolutePath);
    //         if (file.exists()) {

    //             if (file.delete()) {
    //                 return 0;
    //             } 

    //             else {
    //                 return -1;
    //             }
    //         } 

    //         else {
    //             return -2;
    //         }

    //     }

    //     return 0;

    // }




    
}
