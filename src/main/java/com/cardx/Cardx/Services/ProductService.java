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

    public String setProduct(String productDetails) throws Exception {
        ProductRequest pr;
        try {
            String sql = repository.addProductDetails();
            pr = mapper.readValue(productDetails, ProductRequest.class);

//            int ans = jdbcTemplate.update(sql, pr.getProduct_id(), pr.getUser_id(), pr.getCard_name(), pr.getType_card(),
//                    pr.getCard_design_id(), pr.getCard_design_name(), pr.getCard_design_amount());

//            if (ans == 1) {
//                String jsonData = mapper.writeValueAsString(pr);
//                // Event Log
//                eventHelper.logEvent(Constants.STAGE_PRODUCT_DETAILS, pr.getUser_id(), jsonData);
//                return jsonData;
//            } else {
//                return "we faced some technical issue, please try again after sometime";
//            }
        } catch (Exception e) {
            throw new Exception("Error in setProductDetails");
        }

        return null;
    }
}
