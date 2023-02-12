package com.cardx.Cardx.Helper;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Services.AddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class EventHelper {

    @Autowired
    Repository repository;

    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = LogManager.getLogger(EventHelper.class);

    private static final String method = "LogEvent";
    public EventHelper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void logEvent(String stage, Long user_id, String json_data){
        logger.info(method + ": Stage:{} & UserID: {} & JSON Data: {}", stage, user_id, json_data);

        String sql = repository.addEvent();
        Date date = new Date(System.currentTimeMillis());

        jdbcTemplate.update(sql, stage, user_id, json_data, date.toString());
    }
}
