package com.cardx.Cardx.Model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequest {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("first_name")
    private String userFirstName;

    @JsonProperty("last_name")
    private String userLastName;

    @JsonProperty("prefer_name")
    private String userPreferName;

    @JsonProperty("contact")
    private String userContact;

    @JsonProperty("email")
    private String userEmail;

    @JsonProperty("social_media")
    SocialMediaRequest socialMediaRequest;

    @JsonProperty("products")
    ProductRequest productRequest;

    @JsonProperty("address")
    AddressRequest addressRequest;
}
