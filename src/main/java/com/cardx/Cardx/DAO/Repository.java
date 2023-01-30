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
                "pr.product_id, pr.card_name, pr.type_card, " +
                "cd.design_id, cd.design_name, cd.design_amount,  " +
                "sm.instagram, sm.snapchat, sm.social_media_id " +
                "from userdetailsrequest ur, productrequest pr, socialmediarequest sm, carddesign cd " +
                "where 1=1 AND ur.user_id=? AND ur.user_id=pr.user_id AND ur.user_id=sm.user_id AND pr.card_design_id=cd.design_id";
    }

    public String getAllEmails(){
        return "select email from userdetailsrequest ";
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
        return "INSERT INTO eventrequest ( stage, user_id, json_data, event_date) VALUES ( ?, ?, ?, ? )";
    }

    @Transactional
    public String addProductDetails() {
        return "INSERT INTO productsrequest (product_id, user_id, card_name, type_card, card_design_id, card_design_name, card_design_amount) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ? )";
    }

    @Transactional
    public String addCardDesigns() {
        return "INSERT INTO carddesign (design_id, design_name, design_amount) " +
                "VALUES ( ?, ?, ? )";
    }
}
