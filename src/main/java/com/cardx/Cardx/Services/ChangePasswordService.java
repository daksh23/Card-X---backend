package com.cardx.Cardx.Services;

import com.cardx.Cardx.Config.JwtUtil;
import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Model.Request.user.User;
import com.cardx.Cardx.Model.Request.user.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ChangePasswordService {

    private static final Logger logger = LogManager.getLogger(ChangePasswordService.class);
    private static final String method = "changePassword";

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    Repository repository;

    @Autowired
    RowMapperService rowMapperService;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    RegisterUserService registerUserService;

    public ChangePasswordService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ResponseEntity<String> changePassword(String currentPassword, String newPassword, String email) throws Exception {
        logger.debug(method + " method started :: For User={} :: with currentPassword={} and newPassword={}",email, currentPassword, newPassword);

        UserDetails userDetails = null;
        String getUserLoginDetailsSql = repository.getUserByEmail();
        User user = null;
        String userDetailsJson = null;

        try {

            // Query for user details by email
            user = jdbcTemplate.queryForObject(getUserLoginDetailsSql, new Object[]{email}, rowMapperService.rowUser);

            userDetailsJson = user.getUser();

            // Map user details from database based on email user typed
            userDetails = mapper.readValue(userDetailsJson, UserDetails.class);

            if(userDetails != null && userDetails.getPersonalInfo().getCredentials().getPassword().equals(currentPassword)) {
                userDetails.getPersonalInfo().getCredentials().setPassword(newPassword); // set new password as user requested

                userDetails.getPersonalInfo().getCredentials().setRecoveryPassword(currentPassword); // Backup last password for user

                userDetailsJson = mapper.writeValueAsString(userDetails);
                String sql = repository.updateUserJson(); // updated user details with new password

                int rowsAffected = jdbcTemplate.update(sql, userDetailsJson, email);

                if(rowsAffected > 0){
                    return ResponseEntity.status(200).body("{\"Completed\":\"Password changed.\"}");
                }else{
                    return ResponseEntity.status(207).body("{\"Error\":\"Its us not you!\"}");
                }

            }else{
                return ResponseEntity.status(207).body("{\"Error\":\"Invalid current Password\"}");
            }
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(207).body("{\"Error\":\"Invalid current Password\"}");
        }
    }
}
