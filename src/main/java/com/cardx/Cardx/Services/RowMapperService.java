package com.cardx.Cardx.Services;

import com.cardx.Cardx.Helper.Constants;
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

            user.setUserId(rs.getLong(Constants.USER_ID));
            user.setUserFirstName(rs.getString(Constants.FIRST_NAME));
            user.setUserLastName(rs.getString(Constants.LAST_NAME));
            user.setUserContact(rs.getString(Constants.CONTACTS));
            user.setUserEmail(rs.getString(Constants.EMAIL));
            user.setUserPreferName(rs.getString(Constants.PREFER_NAME));

            ProductRequest pr = new ProductRequest();
            pr.setProductId(rs.getLong(Constants.PRODUCT_ID));
            pr.setUserId(rs.getLong(Constants.USER_ID));
            pr.setCardName(rs.getString(Constants.CARD_NAME));
            pr.setTypeCard(rs.getString(Constants.TYPE_CARD));
            user.setProductRequest(pr);

            CardDesigns cd = new CardDesigns();
            cd.setCardDesignId(rs.getString("card_design_id"));
            cd.setCardDesignName(rs.getString("card_design_name"));
            cd.setCardDesignAmount(rs.getString("card_design_amount"));

            SocialMediaRequest sm = new SocialMediaRequest();
            sm.setUserId(rs.getLong(Constants.USER_ID));
            sm.setInstagram(rs.getString("instagram"));
            sm.setSnapchat(rs.getString("snapchat"));
            sm.setSocialMediaId(rs.getLong("social_media_id"));
            user.setSocialMediaRequest(sm);

            return  user;
     };

       public final RowMapper< UserDetailsRequest> rowUserIds = (rs, rowNum) -> {
              UserDetailsRequest user = new UserDetailsRequest();
              user.setUserId(rs.getLong(Constants.USER_ID));
              return user;
       };

       public final RowMapper< UserDetailsRequest> rowEmails = (rs, rowNum) -> {
              UserDetailsRequest user = new UserDetailsRequest();
              user.setUserEmail(rs.getString(Constants.EMAIL));
              return user;
       };

}
