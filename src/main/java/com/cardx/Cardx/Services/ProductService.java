package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Helper.EventHelper;
import com.cardx.Cardx.Model.Request.ProductRequest;
import com.cardx.Cardx.Model.Request.UserDetailsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    Repository repository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    EventHelper eventHelper;

    private static final Logger logger = LogManager.getLogger(ProductService.class);
    private static final String method = "setProduct";

    public ProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<String> setProduct(String productDetails) throws Exception {
        logger.debug(method + ": Product Details: {}", productDetails);

        ProductRequest pr;
        try {
            String sql = repository.addProductDetails();
            pr = mapper.readValue(productDetails, ProductRequest.class);

            int ans = jdbcTemplate.update(sql, pr.getUserId(), pr.getCardDesignId(), pr.getCardName(), pr.getTypeCard());

            if (ans == 1) {
                String product = mapper.writeValueAsString(pr);
                // Event Log
                eventHelper.logEvent(Constants.STAGE_PRODUCT_DETAILS_ADD, pr.getUserId(), product);
                return ResponseEntity.status(200).body(product);
            } else {
                return ResponseEntity.status(207).body(Constants.ERROR_MSG_FOR_NOT_ADD_DATA);
            }
        } catch (Exception e) {
            throw new Exception("Error in setProductDetails");
        }
    }
}
