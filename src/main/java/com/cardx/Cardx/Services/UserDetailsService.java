package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Helper.EventHelper;
import com.cardx.Cardx.Model.Request.UserDetailsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserDetailsService {

    @Autowired
    Repository repository;

    @Autowired
    EventHelper eventHelper;

    @Autowired
    RowMapperService rowMapperService;

    private JdbcTemplate jdbcTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    public UserDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String setUserDetails(String userDetails) throws Exception {
        UserDetailsRequest ur;
        try{
            String sql = repository.addUserDetails();
            ur = mapper.readValue(userDetails, UserDetailsRequest.class);
            int ans = jdbcTemplate.update(sql, ur.getUser_id(), ur.getUser_first_name(), ur.getUser_last_name(), ur.getUser_prefer_name(),
                    ur.getUser_contact(), ur.getUser_email());
            if(ans == 1){
                String jsonData = mapper.writeValueAsString(ur);
                // Event Log
                eventHelper.logEvent(Constants.STAGE_USER_DETAILS, ur.getUser_id(), jsonData);
               return jsonData;
            }else{
               return "we faced some technical issue, please try again after sometime";
            }
        }catch (Exception e){
            throw new Exception("Error in setUserDetails");
        }
    }
}
