package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Helper.EventHelper;
import com.cardx.Cardx.Model.Request.SocialMediaRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private static final Logger logger = LogManager.getLogger(SocialMediaService.class);
    private static final String method = "addSocialMediaDetails";

    private final ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<String> addSocialMediaDetails(String  socialMediaDetails ) throws Exception {
        logger.debug(method + ": SocialMedia Details: {}", socialMediaDetails );

        SocialMediaRequest sm;

        try {
            String sql = repository.addSocialMedia();
            sm = mapper.readValue(socialMediaDetails, SocialMediaRequest.class);

            // social_media_id, user_id, snapchat, instagram
            int ans = jdbcTemplate.update(sql, sm.getSocialMediaId(), sm.getUserId(), sm.getSnapchat(), sm.getInstagram());
            if(ans == 1){
                String socialMedia = mapper.writeValueAsString(sm);
                eventHelper.logEvent(Constants.STAGE_SOCIAL_MEDIA_ADD, sm.getUserId(), socialMedia);
                return ResponseEntity.status(200).body(socialMedia);
            }else{
                return ResponseEntity.status(207).body(Constants.ERROR_MSG_FOR_NOT_ADD_DATA);
            }
        } catch (JsonMappingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
