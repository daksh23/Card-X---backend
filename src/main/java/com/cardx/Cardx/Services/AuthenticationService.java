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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthenticationService {

    private static final Logger logger = LogManager.getLogger(AuthenticationService.class);
    private static final String method = "login";

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    Repository repository;

    @Autowired
    private JwtUtil jwtUtil;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    RowMapperService rowMapperService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public ResponseEntity<String> login(String email, String password) throws Exception {
        logger.debug(method + " method started :: with email={} and password={}",email, password);

        UserDetails userDetails = null;
        String getUserLoginDetailsSql = repository.getUserByEmail();
        String userDetailsPassword = null;
        String userDetailsEmail = null;
        String tokenForUser = null;
        User user = null;

        try {
            // Query for user details by email
            user = jdbcTemplate.queryForObject(getUserLoginDetailsSql, new Object[]{email}, rowMapperService.rowUser);

            String userDetailsJson = user.getUser(); // Get all personal Details from database as string format

            // Map user details from database based on email user typed
            userDetails = mapper.readValue(userDetailsJson, UserDetails.class);

            if(userDetails != null){
                userDetailsPassword = userDetails.getPersonalInfo().getCredentials().getPassword();
                userDetailsEmail = userDetails.getPersonalInfo().getEmail();
            }

            logger.debug(method + " String format userDetails={} :: and object format={} ::", userDetailsJson, userDetails);

            if (userDetails == null
                    || userDetails.getPersonalInfo() == null
                    || userDetails.getPersonalInfo().getUserId() == null
                    || userDetailsEmail == null
                    || !Objects.equals(userDetailsEmail, email)
                    || userDetails.getPersonalInfo().getCredentials() == null
                    || !Objects.equals(userDetailsPassword, password)
            ) {
                return ResponseEntity.status(207).body("{\"Error\":\"Invalid Email or Password\"}");
            }
            // Generate JWT token for user login
            tokenForUser = jwtUtil.generateToken(userDetailsEmail);

        } catch (EmptyResultDataAccessException e) {
            // Handle case where no user is found with the provided email
            logger.debug("No user found with email: {}", email);
            return ResponseEntity.status(207).body("{\"Error\":\"Invalid Email or Password\"}");
        }
        logger.debug(method + " method ended :: with user token={}", tokenForUser);
        return ResponseEntity.status(200).body("{\"Token\":\"" + tokenForUser + "\"}");
    }
}
