package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Helper.EventHelper;
import com.cardx.Cardx.Model.Request.AddressRequest;
import com.cardx.Cardx.Model.Request.SocialMediaRequest;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private static final Logger logger = LogManager.getLogger(AddressService.class);

    private static final String method = "addAddress";
    public ResponseEntity<String> addAddress(String addressDetails ) throws Exception {
        logger.debug(method + ": Address Details: {}", addressDetails);
        AddressRequest addr;

        try {
            String sql = repository.addAddress();
            addr = mapper.readValue(addressDetails, AddressRequest.class);

            // user_id, unit_no, city,postal_code, province, country
            int ans = jdbcTemplate.update(sql, addr.getUserId(), addr.getUnitNo(), addr.getCity(),
                    addr.getPostalCode(), addr.getProvince(), addr.getCountry());
            if(ans == 1){
                String address = mapper.writeValueAsString(addr);
                eventHelper.logEvent(Constants.STAGE_ADDRESS_ADD, addr.getUserId(), address);
                return ResponseEntity.status(200).body(address);
            }else{
                return ResponseEntity.status(207).body("Facing problem at serverSide");
            }
        } catch (JsonMappingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
