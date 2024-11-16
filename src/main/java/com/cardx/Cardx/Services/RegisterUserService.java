package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Util;
import com.cardx.Cardx.Model.Request.user.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

@Component
public class RegisterUserService {

    @Autowired
    Repository repository;

    @Autowired
    Util util;

    @Autowired
    private Environment env;

    private final JdbcTemplate jdbcTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LogManager.getLogger(RegisterUserService.class);

    public RegisterUserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ResponseEntity<String> addUser(String registerDetails) throws Exception {
        logger.debug("addUser: {}", registerDetails );

        UserDetails userDetails = null;
        String email = null;
        String userName = null;

        try{
            String sql = repository.registerUser();
            String date =  util.getCurrentDate();
            String userid = util.generateUUID();
            userDetails = mapper.readValue(registerDetails, UserDetails.class);

            // Extract email from userDetails - and validate its not null
            if(userDetails.getPersonalInfo().getEmail() == null
                    || userDetails.getPersonalInfo().getEmail().isEmpty()
                    || userDetails.getPersonalInfo().getEmail().isBlank()){
                return ResponseEntity.status(207).body("{'Error':'Registration Fail because of Email.'}");
            }// Extract passwords from userDetails - and validate its not null and not matched
            else if (userDetails.getPersonalInfo().getCredentials() == null
                    || userDetails.getPersonalInfo().getCredentials().getPassword() == null
                    || userDetails.getPersonalInfo().getCredentials().getRecoveryPassword() == null
            ) {
                return ResponseEntity.status(207).body("{'Error':'Registration Fail because of passwords not matched.'}");
            } else{
                email = userDetails.getPersonalInfo().getEmail();
                userName = util.extractUserNameFromEmailForProfileImage(email); // mark email first part as userName of that user
                userDetails.getPersonalInfo().setUserName(userName); // Set UserName

                if(!"false".equals(userDetails.getPersonalInfo().getImage())){
                    userDetails.getPersonalInfo().setImage(userName); // set email as image name - ui will update profile image name as email
                }
                userDetails.getPersonalInfo().setUserId(userid); // set userID as UUID
            }

            // Converated userDetails with updated values into json
            String userDetailsJson = mapper.writeValueAsString(userDetails);
            int ans = jdbcTemplate.update(sql, userid, userName, email, userDetailsJson,date);

            if(ans == 1){
                return ResponseEntity.status(200).body("{'Complete':'Registration Completed.'}");
            }else{
                // Delete uploaded image if registration fails
                boolean imgDelete = deleteProfileImageIfRegistrationFailed(userName);
                logger.debug(" response from deleteProfileImageIfRegistrationFailed :: " + imgDelete);

                return ResponseEntity.status(207).body("{'Error':'Registration Fail.'}");
            }
        }catch (Exception e){
            // Delete uploaded image if registration fails
            deleteProfileImageIfRegistrationFailed(userName);
            throw new Exception("Error in setUserDetails");
        }
    }

    private boolean deleteProfileImageIfRegistrationFailed(String fileName) throws IOException {
        logger.debug("deleteProfileImageIfRegistrationFailed ::fileName :: " + fileName);
        String UPLOAD_DIR = env.getProperty("user.profile.image.location");
        final Path root = Paths.get(UPLOAD_DIR);

        Path file = root.resolve(fileName);
        return Files.deleteIfExists(file);
    }

}
