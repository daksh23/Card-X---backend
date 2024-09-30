package com.cardx.Cardx.Services;

import com.cardx.Cardx.Helper.Constants;
import com.cardx.Cardx.Model.Request.*;
import com.cardx.Cardx.Model.Request.user.User;
import com.cardx.Cardx.Model.Request.user.UserDetails;
import com.cardx.Cardx.Model.Response.EmailResponse;
import com.cardx.Cardx.Model.Response.FeaturesResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import static com.cardx.Cardx.Helper.Constants.EMAIL;

@Component
public class RowMapperService {

       private static final Logger logger = LogManager.getLogger(RowMapperService.class);

       public final RowMapper<CardDesigns> rowCardDesign = (rs, rowNum) -> {
              logger.debug("rowCardDesign: {} ", rs );
              CardDesigns cardDesigns = new CardDesigns();

              cardDesigns.setCardDesignId(rs.getLong(Constants.DESIGN_ID));
              cardDesigns.setCardDesignName((rs.getString(Constants.DESIGN_NAME)));
              cardDesigns.setCardDesignAmount(rs.getString(Constants.DESIGN_AMOUNT));
              cardDesigns.setCardDesignCollection(rs.getString(Constants.DESIGN_COLLECTION));
              cardDesigns.setCardDesignImage(rs.getString(Constants.DESIGN_IMAGE));
              cardDesigns.setDmyUserName(rs.getString(Constants.DESIGN_DMY_USER_NAME));
              cardDesigns.setCardDesignType(rs.getString(Constants.DESIGN_TYPE));

              return cardDesigns;
       };

       public final RowMapper<FeaturesResponse> rowFeatures = (rs, rowNum) -> {
              logger.debug("rowFeatures: {} ", rs );
              FeaturesResponse featuresResponse = new FeaturesResponse();

              featuresResponse.setDescription(rs.getString(Constants.FEATURE_DESCRIPTION));
              featuresResponse.setIcon(rs.getString(Constants.FEATURE_ICON));
              featuresResponse.setTitle(rs.getString(Constants.FEATURE_TITLE));
              featuresResponse.setFeature_id(rs.getInt(Constants.FEATURE_ID));

              return featuresResponse;
       };

       public final RowMapper<EmailResponse> rowEmail = (rs, rowNum) -> {
              logger.debug("rowEmail: {} ", rs );

              EmailResponse emailResponse = new EmailResponse();
              emailResponse.setEmail(rs.getString(Constants.EMAIL));

              return emailResponse;
       };

       public final RowMapper<User> rowUser = (rs, rowNum) -> {
              logger.debug("rowEmail: {} ", rs );

              User user = new User();
              user.setEmail(rs.getString(Constants.EMAIL));
              user.setUser(rs.getString(Constants.USER));
              user.setUser_id(rs.getString(Constants.USER_ID));
              user.setUsername(rs.getString(Constants.USER_NAME));
              user.setTimeDate(rs.getString(Constants.USER_TIME));

              return user;
       };


}
