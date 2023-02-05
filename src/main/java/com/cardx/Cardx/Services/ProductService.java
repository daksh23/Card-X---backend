package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Helper.EventHelper;
import com.cardx.Cardx.Model.Request.ProductRequest;
import com.cardx.Cardx.Model.Request.UserDetailsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    Repository repository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    EventHelper eventHelper;

    public ProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final ObjectMapper mapper = new ObjectMapper();

    public String  setProduct(String productDetails) throws Exception {
        ProductRequest pr;
        try {
            String sql = repository.addProductDetails();
            pr = mapper.readValue(productDetails, ProductRequest.class);

            int ans = jdbcTemplate.update(sql, pr.getUserId(), pr.getCardDesignId(), pr.getCardName(), pr.getTypeCard());

            if (ans == 1) {
                String jsonData = mapper.writeValueAsString(pr);
                // Event Log
                eventHelper.logEvent(Constants.STAGE_PRODUCT_DETAILS_ADD, pr.getUserId(), jsonData);
                return jsonData;
            } else {
                return "we faced some technical issue, please try again after sometime";
            }
        } catch (Exception e) {
            throw new Exception("Error in setProductDetails");
        }
    }
}
