package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Helper.EventHelper;
import com.cardx.Cardx.Model.Request.SocialMediaRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SocialMediaService {

    @Autowired
    Repository repository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    EventHelper eventHelper;

    public SocialMediaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final ObjectMapper mapper = new ObjectMapper();

    public String addSocialMediaDetails(String  socialMediaDetails ) throws Exception {
        SocialMediaRequest sm;

        try {
            String sql = repository.addSocialMedia();
            sm = mapper.readValue(socialMediaDetails, SocialMediaRequest.class);

            // social_media_id, user_id, snapchat, instagram
            int ans = jdbcTemplate.update(sql, sm.getSocialMediaId(), sm.getUserId(), sm.getSnapchat(), sm.getInstagram());
            if(ans == 1){
                String jsonData = mapper.writeValueAsString(sm);
                eventHelper.logEvent(Constants.STAGE_SOCIAL_MEDIA_ADD, sm.getUserId(), jsonData);
                return jsonData;
            }else{
                return "we faced some technical issue, please try again after sometime";
            }
        } catch (JsonMappingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
