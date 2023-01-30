package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Helper.EventHelper;
import com.cardx.Cardx.Model.Request.CardDesigns;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CardDesignsService {

    @Autowired
    Repository repository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    EventHelper eventHelper;

    public CardDesignsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final ObjectMapper mapper = new ObjectMapper();

    public String addCardDesigns(String carddesign) throws Exception {

        CardDesigns cd;

        try {
            String sql = repository.addCardDesigns();
            cd = mapper.readValue(carddesign, CardDesigns.class);

            // design_id, design_name, design_amount
            int ans = jdbcTemplate.update(sql, cd.getCardDesignId(), cd.getCardDesignName(), cd.getCardDesignAmount());
            if(ans == 1){
               return mapper.writeValueAsString(cd);
            }else{
                return "we faced some technical issue, please try again after sometime";
            }
        }catch (Exception e) {
            throw new Exception("Error in setCardDesigns");
        }
    }
}
