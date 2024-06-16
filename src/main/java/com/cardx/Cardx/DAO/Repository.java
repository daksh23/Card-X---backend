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
                "pr.card_design_id, pr.card_name, pr.type_card, " +
                "ad.unit_no, ad.city, ad.postal_code, ad.province, ad.country, " +
                "sm.instagram, sm.snapchat, sm.social_media_id " +
                "from userdetailsrequest ur, productrequest pr, socialmediarequest sm, addressrequest ad " +
                "where 1=1 AND ur.user_id=? AND " +
                "ur.user_id=pr.user_id AND " +
                "ur.user_id=sm.user_id AND " +
                "ad.user_id=ur.user_id";
    }

    public String getAllEmails(){
        return "select email from userdetailsrequest ";
    }

    public String getAllUserId(){
        return "select user_id from userdetailsrequest ";
    }

    public String getAllCardDesigns(){
        return "select * from carddesigns";
    }

//    getCardDesignById
    public String getCardDesignById(Long id){
        return "select * from carddesigns where design_id=?";
    }

    public String getCardDesign(){
        return "select * from carddesigns";
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
        return "INSERT INTO productrequest (user_id, card_design_id, card_name, type_card) " +
                "VALUES ( ?, ?, ?, ? )";
    }

    @Transactional
    public String addCardDesigns() {
        return "INSERT INTO carddesigns (designName, image, collection, dmyUserName, designAmount) " +
                "VALUES ( ?, ?, ? ,?, ?, ? )";
    }

    @Transactional
    public String addSocialMedia() {
        return "INSERT INTO socialmediarequest(social_media_id, user_id, snapchat, instagram) " +
                "VALUES (?,? ,? ,?) ";
    }

    @Transactional
    public String addAddress(){
        return "INSERT INTO addressrequest ( user_id, unit_no, city, postal_code, province, country) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
    }


}
