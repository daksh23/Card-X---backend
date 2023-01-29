package com.cardx.Cardx.Helper;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Model.Request.EventRequest;
import com.cardx.Cardx.Model.Request.UserDetailsRequest;
import com.cardx.Cardx.Services.RowMapperService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EventHelper {

    @Autowired
    Repository repository;

    private JdbcTemplate jdbcTemplate;

    public void logEvent(String stage, int event_id, Long user_id, String json_data, Date date){
        String sql = repository.addEvent();
        jdbcTemplate.update(sql, event_id, stage, user_id, json_data, date);
    }
}
