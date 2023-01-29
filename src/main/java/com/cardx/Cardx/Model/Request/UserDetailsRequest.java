package com.cardx.Cardx.Model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequest {

    @JsonProperty("user_id")
    private Long user_id;

    @JsonProperty("first_name")
    private String user_first_name;

    @JsonProperty("last_name")
    private String user_last_name;

    @JsonProperty("prefer_name")
    private String user_prefer_name;

    @JsonProperty("contact")
    private BigInteger user_contact;

    @JsonProperty("email")
    private String user_email;

    @JsonProperty("social_media")
    SocialMediaRequest socialMediaRequest;

    @JsonProperty("products")
    ProductRequest productRequest;
}
