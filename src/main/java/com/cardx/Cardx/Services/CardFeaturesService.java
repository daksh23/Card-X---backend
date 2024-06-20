package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Model.Request.CardDesigns;
import com.cardx.Cardx.Model.Response.FeaturesResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardFeaturesService {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(CardFeaturesService.class);

    @Autowired
    RowMapperService rowMapperService;

    @Autowired
    Repository repository;

    public CardFeaturesService(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    public String retrieveFeatures() throws JsonProcessingException {
        logger.debug("method : retrieveFeatures");
        String sql = repository.getFeatures();
        List<FeaturesResponse> featuresList =  jdbcTemplate.query(sql, rowMapperService.rowFeatures);
        return mapper.writeValueAsString(featuresList);
    }
}
