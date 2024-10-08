package com.cardx.Cardx.Services;

import com.cardx.Cardx.Config.JwtUtil;
import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Model.Request.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {


    private static final Logger logger = LogManager.getLogger(ProfileService.class);
    private static final String method = "retrieveProfileInformation";

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    Repository repository;

    @Autowired
    private JwtUtil jwtUtil;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    RowMapperService rowMapperService;

    public ProfileService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public ResponseEntity<String> retrieveProfileInformation(String email){

        String getUserLoginDetailsSql = repository.getUserByEmail();
        User user = null;
        String userDetailsJson = null;

        try {
            // Query for user details by email
            user = jdbcTemplate.queryForObject(getUserLoginDetailsSql, new Object[]{email}, rowMapperService.rowUser);

            logger.debug(method + " User data ={} ::", user);

            if(user != null)
                userDetailsJson = user.getUser(); // Get all personal Details from database as string format
            else
                return ResponseEntity.status(207).body("{\"Error\":\"Data is Empty\"}");

            // Map user details from database based on email user typed
            logger.debug(method + " User personal data in string format={} ::", userDetailsJson);

        } catch (EmptyResultDataAccessException e) {
            // Handle case where no user is found with the provided email
            logger.debug("No data found with given email: {}", email);
            return ResponseEntity.status(207).body("{\"Error\":\"Data is Empty\"}");
        }

        return ResponseEntity.status(200).body(userDetailsJson);
    }

}
