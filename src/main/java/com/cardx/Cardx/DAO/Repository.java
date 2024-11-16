package com.cardx.Cardx.DAO;

import com.cardx.Cardx.Model.Request.user.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Repository implements RepositoryInterface {

     /*
    * Get Data
    * Apis
    */
    public String getCardDesign(){
        return "select * from carddesigns";
    }

    public String getFeatures(){
        return "select * from features";
    }

    public String getEmails(){
        return "select email from users";
    }

    public String getUserByEmail(){
        return "select * from users where email=?";
    }

    public String getUserByUserName(){
        return "select * from users where username=?";
    }

     /*
     * Add Data
     * Apis
     */
    @Transactional
    public String registerUser() {
        return "INSERT INTO users (user_id, username, email, user, time) " +
                "VALUES ( ?, ?, ?, ?, ? )";
    }

    @Transactional
    public String updateUserDetailsJson() {
        return " UPDATE users SET user=? WHERE userName=? ";
    }

    @Transactional
    public String addHelp(){
        return "INSERT INTO help (userName, email, phoneNumber, subject, question, help_image) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Transactional
    public String addHelpMessages(){
        return "INSERT INTO helpmessage (help_ref_id, email, subject, question, help_image) VALUES (?, ?, ?, ?, ?)";
    }

    @Transactional
    public String updateUserJson() {
        return "UPDATE users SET user = ? " + "WHERE email = ? ";
    }

}
