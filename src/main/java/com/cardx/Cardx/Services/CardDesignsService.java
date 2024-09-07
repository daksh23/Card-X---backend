package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Model.Request.CardDesigns;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardDesignsService {

    @Autowired
    Repository repository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    RowMapperService rowMapperService;

    public CardDesignsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LogManager.getLogger(CardDesignsService.class);

    public String getCardDesign() throws JsonProcessingException {
        logger.debug("method : getCardDesign");
        String sql = repository.getCardDesign();
        List<CardDesigns> cardDesign =  jdbcTemplate.query(sql, rowMapperService.rowCardDesign);
        return mapper.writeValueAsString(cardDesign);
    }
}
