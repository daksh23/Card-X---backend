package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Model.Response.EmailResponse;
import com.cardx.Cardx.Model.Response.FeaturesResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationDataForUIService {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(ValidationDataForUIService.class);

    @Autowired
    RowMapperService rowMapperService;

    @Autowired
    Repository repository;

    public ValidationDataForUIService(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    public String retrieveEmails() throws JsonProcessingException {
        logger.debug("method : retrieveEmails");
        String sql = repository.getEmails();
        List<EmailResponse> emailList =  jdbcTemplate.query(sql, rowMapperService.rowEmail);
        return mapper.writeValueAsString(emailList);
    }

}
