package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Model.Request.UserDetailsRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Get Data Apis
// Version 1

@Component
public class GetCardService {

    @Autowired
    Repository repository;

    @Autowired
    RowMapperService rowMapperService;

    private final JdbcTemplate jdbcTemplate;

    private  ObjectMapper Obj = new ObjectMapper();

    public GetCardService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getUserById(Long id) throws JsonProcessingException {
        String sql = repository.getUserId(id);
        return Obj.writeValueAsString(jdbcTemplate.query(sql, rowMapperService.rowMapper, id));
    }

    public List<String> getAllEmails(){
        String sql = repository.getAllEmails();
        List<UserDetailsRequest> emails =  jdbcTemplate.query(sql, rowMapperService.rowEmails);
        return emails.stream().map(UserDetailsRequest::getUser_email).toList();
    }

    public List<Long> getAllUserId(){
        String sql = repository.getAllUserId();
        List<UserDetailsRequest> userIds =  jdbcTemplate.query(sql, rowMapperService.rowUserIds);
        return userIds.stream().map(UserDetailsRequest::getUser_id).toList();
    }

}
