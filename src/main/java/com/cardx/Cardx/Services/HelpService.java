package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Helper.EventHelper;
import com.cardx.Cardx.Model.Request.HelpRequest;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class HelpService {

    @Autowired
    Repository repository;

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    EventHelper eventHelper;

    public HelpService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(HelpService.class);

    private static final String method = "addHelp";

    public static final SecureRandom RANDOM = new SecureRandom();

    public ResponseEntity<String> addHelp(String helpDetails) throws Exception {
        logger.debug(method + ": Help Details: {}", helpDetails);
        HelpRequest helpData = null;

        try {
            String sql = repository.addHelp();
            helpData = mapper.readValue(helpDetails, HelpRequest.class);

            // user name, email, phone_number, subject, question, image( optional)
            int response = jdbcTemplate.update(sql, helpData.getUserName(), helpData.getEmail(), helpData.getPhoneNumber(), helpData.getSubject(), helpData.getQuestion(), helpData.getImage());

            if(response == 1){
                String refNumber = generateRefNumber();
                String sqlForMsg = repository.addHelpMessages();

                // email, email, subject, question, image( optional)
                int res = jdbcTemplate.update(sqlForMsg, refNumber, helpData.getEmail(), helpData.getSubject(), helpData.getQuestion(), helpData.getImage());

                if(res == 1){
                    return ResponseEntity.status(200).body(refNumber);
                }else{
                    return ResponseEntity.status(207).body("Facing problem at serverSide");
                }
            }else{
                return ResponseEntity.status(207).body("Facing problem at serverSide");
            }
        } catch (JsonMappingException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String generateRefNumber() {
        StringBuilder refNumber = new StringBuilder(Constants.TOTAL_LENGTH);

        // Add letters at the beginning
        for (int i = 0; i < Constants.LETTERS_LENGTH; i++) {
            int index = RANDOM.nextInt(Constants.LETTERS.length());
            refNumber.append(Constants.LETTERS.charAt(index));
        }

        // Add digits in the middle
        for (int i = 0; i < Constants.DIGITS_LENGTH; i++) {
            int index = RANDOM.nextInt(Constants.DIGITS.length());
            refNumber.append(Constants.DIGITS.charAt(index));
        }

        // Add letters at the end
        for (int i = 0; i < Constants.LETTERS_LENGTH; i++) {
            int index = RANDOM.nextInt(Constants.LETTERS.length());
            refNumber.append(Constants.LETTERS.charAt(index));
        }

        return refNumber.toString();
    }

}