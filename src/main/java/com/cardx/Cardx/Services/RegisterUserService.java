package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Util;
import com.cardx.Cardx.Model.Request.user.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RegisterUserService {

    @Autowired
    Repository repository;

    @Autowired
    Util util;

    private final JdbcTemplate jdbcTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LogManager.getLogger(RegisterUserService.class);
    private static final String method = "setUserDetails";

    public RegisterUserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ResponseEntity<String> addUser(String registerDetails) throws Exception {
        logger.debug(method + ": addUser: {}", registerDetails );

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
            else if (userDetails.getPersonalInfo() == null
                    || userDetails.getPersonalInfo().getCredentials() == null
                    || userDetails.getPersonalInfo().getCredentials().getPassword() == null
                    || userDetails.getPersonalInfo().getCredentials().getConfirmPassword() == null
                    || !Objects.equals(userDetails.getPersonalInfo().getCredentials().getConfirmPassword(), userDetails.getPersonalInfo().getCredentials().getConfirmPassword())
            ) {
                return ResponseEntity.status(207).body("{'Error':'Registration Fail because of passwords not matched.'}");
            } else{
                email = userDetails.getPersonalInfo().getEmail();
                userName = util.extractUserNameFromEmailForProfileImage(email); // mark email first part as userName of that user
                userDetails.getPersonalInfo().setImage(userName); // set email as image name - ui will update profile image name as email
                userDetails.getPersonalInfo().setUserId(userid); // set userID as UUID
            }

            // Converated userDetails with updated values into json
            String userDetailsJson = mapper.writeValueAsString(userDetails);

            int ans = jdbcTemplate.update(sql, userid, userName, email, userDetailsJson,date);

            if(ans == 1){
                return ResponseEntity.status(200).body("{'Complete':'Registration Completed.'}");
            }else{
                return ResponseEntity.status(207).body("{'Error':'Registration Fail.'}");
            }
        }catch (Exception e){
            throw new Exception("Error in setUserDetails");
        }
    }

}
