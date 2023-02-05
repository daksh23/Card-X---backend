package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Helper.EventHelper;
import com.cardx.Cardx.Model.Request.AddressRequest;
import com.cardx.Cardx.Model.Request.SocialMediaRequest;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    Repository repository;

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    EventHelper eventHelper;

    public AddressService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final ObjectMapper mapper = new ObjectMapper();

    public String addAddress(String addressDetails ) throws Exception {
        AddressRequest address;

        try {
            String sql = repository.addAddress();
            address = mapper.readValue(addressDetails, AddressRequest.class);

            // user_id, unit_no, city,postal_code, province, country
            int ans = jdbcTemplate.update(sql, address.getUserId(), address.getUnitNo(), address.getCity(),
                    address.getPostalCode(), address.getProvince(), address.getCountry());
            if(ans == 1){
                String jsonData = mapper.writeValueAsString(address);
                eventHelper.logEvent(Constants.STAGE_ADDRESS_ADD, address.getUserId(), jsonData);
                return jsonData;
            }else{
                return "we faced some technical issue, please try again after sometime";
            }
        } catch (JsonMappingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
