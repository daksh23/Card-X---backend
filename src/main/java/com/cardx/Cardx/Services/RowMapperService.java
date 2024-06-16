package com.cardx.Cardx.Services;

import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Model.Request.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class RowMapperService {

       private static final Logger logger = LogManager.getLogger(RowMapperService.class);
     public final RowMapper< UserDetailsRequest> rowMapper = (rs, rowNum) -> {
            logger.debug("rowMapper: {} ", rs );

            UserDetailsRequest user = new UserDetailsRequest();

            user.setUserId(rs.getLong(Constants.USER_ID));
            user.setUserFirstName(rs.getString(Constants.FIRST_NAME));
            user.setUserLastName(rs.getString(Constants.LAST_NAME));
            user.setUserContact(rs.getString(Constants.CONTACT));
            user.setUserEmail(rs.getString(Constants.EMAIL));
            user.setUserPreferName(rs.getString(Constants.PREFER_NAME));

            ProductRequest pr = new ProductRequest();
            pr.setCardDesignId(rs.getLong(Constants.CARD_DESIGN_ID));
            pr.setCardName(rs.getString(Constants.CARD_NAME));
            pr.setTypeCard(rs.getString(Constants.TYPE_CARD));
            user.setProductRequest(pr);

            SocialMediaRequest sm = new SocialMediaRequest();
            sm.setUserId(rs.getLong(Constants.USER_ID));
            sm.setInstagram(rs.getString(Constants.INSTAGRAM));
            sm.setSnapchat(rs.getString(Constants.SNAPCHAT));
            sm.setSocialMediaId(rs.getLong(Constants.SOCIAL_MEDIA_ID));
            user.setSocialMediaRequest(sm);

            // ad.unit_no, ad.city, ad.postal_code, ad.province, ad.country,
            AddressRequest ad = new AddressRequest();
            ad.setUnitNo(rs.getLong(Constants.UNIT_NO));
            ad.setCity(rs.getString(Constants.CITY));
            ad.setPostalCode(rs.getString(Constants.POSTAL_CODE));
            ad.setProvince(rs.getString(Constants.PROVINCE));
            ad.setCountry(rs.getString(Constants.COUNTRY));
            user.setAddressRequest(ad);

            return  user;
     };

       public final RowMapper< UserDetailsRequest> rowUserIds = (rs, rowNum) -> {
              logger.debug("rowUserIds: {} ", rs );

              UserDetailsRequest user = new UserDetailsRequest();
              user.setUserId(rs.getLong(Constants.USER_ID));
              return user;
       };

       public final RowMapper< UserDetailsRequest> rowEmails = (rs, rowNum) -> {
              logger.debug("rowEmails: {} ", rs );
              UserDetailsRequest user = new UserDetailsRequest();
              user.setUserEmail(rs.getString(Constants.EMAIL));
              return user;
       };

       public final RowMapper<CardDesigns> rowCardDesign = (rs, rowNum) -> {
              logger.debug("rowCardDesign: {} ", rs );
              CardDesigns cardDesigns = new CardDesigns();

              cardDesigns.setCardDesignId(rs.getLong(Constants.DESIGN_ID));
              cardDesigns.setCardDesignName((rs.getString(Constants.DESIGN_NAME)));
              cardDesigns.setCardDesignAmount(rs.getString(Constants.DESIGN_AMOUNT));
              cardDesigns.setCardDesignCollection(rs.getString(Constants.DESIGN_COLLECTION));
              cardDesigns.setCardDesignImage(rs.getString(Constants.DESIGN_IMAGE));
              cardDesigns.setDmyUserName(rs.getString(Constants.DESIGN_DMY_USER_NAME));

              return cardDesigns;
       };
}
