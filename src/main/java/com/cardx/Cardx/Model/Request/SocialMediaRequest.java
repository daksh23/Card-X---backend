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
public class SocialMediaRequest {

    @JsonProperty("social_media_id")
    private Long socialMediaId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("snapchat")
    private String snapchat;

    @JsonProperty("instagram")
    private String instagram;

}
