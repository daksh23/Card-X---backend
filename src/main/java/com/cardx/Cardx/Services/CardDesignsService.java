package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Helper.EventHelper;
import com.cardx.Cardx.Model.Request.CardDesigns;
import com.cardx.Cardx.Model.Request.UserDetailsRequest;
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

    public ResponseEntity<String> addCardDesigns(String cardDesign) throws Exception {
        logger.debug("method: addCardDesigns & Card Design: {}", cardDesign);

        CardDesigns cd;

        try {
            String sql = repository.addCardDesigns();
            cd = mapper.readValue(cardDesign, CardDesigns.class);

            // design_id, design_name, design_amount
            int ans = jdbcTemplate.update(sql, cd.getCardDesignId(), cd.getCardDesignName(), cd.getCardDesignAmount());
            if(ans == 1){
               String design = mapper.writeValueAsString(cd);
               return ResponseEntity.status(200).body(design);
            }else{
                return ResponseEntity.status(207).body(Constants.ERROR_MSG_FOR_NOT_ADD_DATA);
            }
        }catch (Exception e) {
            throw new Exception("Error in setCardDesigns");
        }
    }

    public String getCardDesignById(Long id) throws JsonProcessingException {
        logger.debug("method : getCardDesignById & User ID : {}", id);
        String sql = repository.getCardDesignById(id);
        List<CardDesigns> cardDesign =  jdbcTemplate.query(sql, rowMapperService.rowCardDesign, id);
        return mapper.writeValueAsString(cardDesign);
    }
}
