package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Util;
import com.cardx.Cardx.Model.Request.user.User;
import com.cardx.Cardx.Model.Request.user.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class UploadProfile {

    @Autowired
    private Environment env;

    @Autowired
    Repository repository;

    @Autowired
    RowMapperService rowMapperService;

    @Autowired
    Util util;

    private final JdbcTemplate jdbcTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LogManager.getLogger(UploadProfile.class);

    public UploadProfile(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String uploadProfile(MultipartFile file, String fileName){
        logger.debug(" UploadProfile component :: uploadProfile method :: " + file + " with file name :: " + fileName);

        String UPLOAD_DIR = env.getProperty("user.profile.image.location");
        boolean uploadedInd = true;

        // Check if the uploaded file is empty
        if (file.isEmpty() || fileName.isBlank() || fileName.isEmpty()) {
            uploadedInd = false;
            return "{'Error':'No file selected.'}";
        }

        try {
            // Read the image from the uploaded file
            BufferedImage originalImage = ImageIO.read(file.getInputStream());
            if (originalImage == null) {
                uploadedInd= false;
                return "{'Error':'Invalid image file.'}";
            }

            // Create the directory if it doesn't exist
            File uploadFolder = new File(UPLOAD_DIR);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // Define the file name using userId, ensuring its consistent
            String newFileName = UPLOAD_DIR + "/" + fileName + ".jpg";
            File outputfile = new File(newFileName);

            // If the file already exists, delete it
            if (outputfile.exists()) {
                if (!outputfile.delete()) {
                    uploadedInd = false;
                    return "{'Error':'Failed to delete previous image.'}";
                }
            }

            // Write the BufferedImage as a new JPG file
            ImageIO.write(originalImage, "jpg", outputfile);

            return "{'Complete':'file successfully uploaded.'}";
        } catch (IOException e) {
            uploadedInd = false;
            e.printStackTrace();
            return  "{'Error':" + e.getMessage() + "'}";
        }finally {
            if(uploadedInd){
                updateImageNameInDatabase(fileName);
            }
        }
    }

    private void updateImageNameInDatabase(String userName) {
        logger.debug(" UploadProfile component :: updateImageNameInDatabase method :: " + userName);
        String getUserDetailsSql = repository.getUserByUserName();
        String updateSql = repository.updateUserDetailsJson(); // update image name in database
        User user = null;
        String userDetailsJson = null;
        UserDetails userDetails = null;
        boolean returnValue = false;

        try{
            String date =  util.getCurrentDate();

            // Query for user details by email
            user = jdbcTemplate.queryForObject(getUserDetailsSql, new Object[]{userName}, rowMapperService.rowUser);
            logger.debug(" updateImageNameInDatabase User data ={} ::", user);

            if(user != null)
                userDetailsJson = user.getUser(); // Get all personal Details from database as string format

            userDetails = mapper.readValue(userDetailsJson, UserDetails.class);

            if(userDetails != null){
                // Update Image name based on userName
                if(userDetails.getPersonalInfo() != null || (!Objects.equals(userDetails.getPersonalInfo().getImage(), "false"))){
                    // Update image name if image field has default value as false
                    userDetails.getPersonalInfo().setImage(userName);
                    returnValue = true;
                }
            }

            if(returnValue){
                // Converated userDetails with updated values into json
                String userDetailsJsonData = mapper.writeValueAsString(userDetails);
                int ans = jdbcTemplate.update(updateSql, userDetailsJsonData, userName);

                if(ans == 1){
                    logger.debug("Method updateImageNameInDatabase :: userDetailsJsonData ={} ::", userDetailsJsonData);

                }else{
                    logger.error("Method updateImageNameInDatabase :: Error while updating user details in database with new image name");
                }
            }else {
                logger.debug("Method updateImageNameInDatabase :: ReturnValue flag value :: " + returnValue);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
