package com.cardx.Cardx.Services;

import com.cardx.Cardx.Model.Request.CardDesigns;
import com.cardx.Cardx.Model.Request.ProductRequest;
import com.cardx.Cardx.Model.Request.SocialMediaRequest;
import com.cardx.Cardx.Model.Request.UserDetailsRequest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class RowMapperService {

     public final RowMapper< UserDetailsRequest> rowMapper = (rs, rowNum) -> {
            UserDetailsRequest user = new UserDetailsRequest();

            user.setUserId(rs.getLong("user_id"));
            user.setUserFirstName(rs.getString("first_name"));
            user.setUserLastName(rs.getString("last_name"));
            user.setUserContact(rs.getString("contact"));
            user.setUserEmail(rs.getString("email"));
            user.setUserPreferName(rs.getString("prefer_name"));

            ProductRequest pr = new ProductRequest();
            pr.setProductId(rs.getLong("product_id"));
            pr.setUserId(rs.getLong("user_id"));
            pr.setCardName(rs.getString("card_name"));
            pr.setTypeCard(rs.getString("type_card"));
            user.setProductRequest(pr);

            CardDesigns cd = new CardDesigns();
            cd.setCardDesignId(rs.getString("card_design_id"));
            cd.setCardDesignName(rs.getString("card_design_name"));
            cd.setCardDesignAmount(rs.getString("card_design_amount"));
            cd.setProductRequest(pr);

            SocialMediaRequest sm = new SocialMediaRequest();
            sm.setUserId(rs.getLong("user_id"));
            sm.setInstagram(rs.getString("instagram"));
            sm.setSnapchat(rs.getString("snapchat"));
            sm.setSocialMediaId(rs.getLong("social_media_id"));
            user.setSocialMediaRequest(sm);

            return  user;
     };

       public final RowMapper< UserDetailsRequest> rowUserIds = (rs, rowNum) -> {
              UserDetailsRequest user = new UserDetailsRequest();
              user.setUserId(rs.getLong("user_id"));
              return user;
       };

       public final RowMapper< UserDetailsRequest> rowEmails = (rs, rowNum) -> {
              UserDetailsRequest user = new UserDetailsRequest();
              user.setUserEmail(rs.getString("email"));
              return user;
       };

}
