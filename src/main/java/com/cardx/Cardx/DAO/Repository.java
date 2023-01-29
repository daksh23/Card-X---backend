package com.cardx.Cardx.DAO;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Repository implements RepositoryInterface {

     /*
    * Get Data
    * Apis
    */

    @Override
    public String getUserId(Long id) {
        return "select ur.user_id, ur.first_name, ur.last_name, ur.prefer_name, ur.contact, ur.email, " +
                "pr.product_id, pr.card_name, pr.type_card, pr.card_design_id, pr.card_design_name, pr.card_design_amount, " +
                "sm.instagram, sm.snapchat, sm.social_media_id " +
                "from userdetailsrequest ur, productrequest pr, socialmediarequest sm " +
                "where 1=1 AND sm.user_id=ur.user_id AND pr.user_id=ur.user_id AND pr.user_id=?";
    }

    public String getAllUserId(){
        return "select user_id from userdetailsrequest ";
    }

     /*
     * Add Data
     * Apis
     */
    @Transactional
    public String addUserDetails() {
        return "INSERT INTO userdetailsrequest (user_id, first_name, last_name, prefer_name, contact, email) " +
                "VALUES ( ?, ?, ?, ?, ?, ? )";
    }

    @Transactional
    public String addEvent() {
        return "INSERT INTO eventrequest (event_id, stage, user_id, json_data, date) " +
                "VALUES ( ?, ?, ?, ?, ? )";
    }
}
