package com.cardx.Cardx.Services;

import com.cardx.Cardx.Model.Request.ProductRequest;
import com.cardx.Cardx.Model.Request.SocialMediaRequest;
import com.cardx.Cardx.Model.Request.UserDetailsRequest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class RowMapperService {

     public final RowMapper< UserDetailsRequest> rowMapper = (rs, rowNum) -> {
            UserDetailsRequest user = new UserDetailsRequest();

            user.setUser_id(rs.getLong("user_id"));
            user.setUser_first_name(rs.getString("first_name"));
            user.setUser_last_name(rs.getString("last_name"));
            user.setUser_contact(BigInteger.valueOf(rs.getInt("contact")));
            user.setUser_email(rs.getString("email"));
            user.setUser_prefer_name(rs.getString("prefer_name"));

            ProductRequest pr = new ProductRequest();
            pr.setProduct_id(rs.getLong("product_id"));
            pr.setUser_id(rs.getLong("user_id"));
            pr.setCard_name(rs.getString("card_name"));
            pr.setType_card(rs.getString("type_card"));
            pr.setCard_design_id(rs.getString("card_design_id"));
            pr.setCard_design_name(rs.getString("card_design_name"));
            pr.setCard_design_amount(rs.getString("card_design_amount"));
            user.setProductRequest(pr);

            SocialMediaRequest sm = new SocialMediaRequest();
            sm.setUser_id(rs.getLong("user_id"));
            sm.setInstagram(rs.getString("instagram"));
            sm.setSnapchat(rs.getString("snapchat"));
            sm.setSocial_media_id(rs.getString("social_media_id"));
            user.setSocialMediaRequest(sm);

            return  user;
     };

       public final RowMapper< UserDetailsRequest> rowUserIds = (rs, rowNum) -> {
              UserDetailsRequest user = new UserDetailsRequest();
              user.setUser_id(rs.getLong("user_id"));
              return user;
       };

}
