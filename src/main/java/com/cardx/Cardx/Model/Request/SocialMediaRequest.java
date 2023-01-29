package com.cardx.Cardx.Model.Request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaRequest {

    @JsonProperty("social_media_id")
    private String social_media_id;

    @JsonIgnore
    private Long user_id;

    @JsonProperty("snapchat")
    private String snapchat;

    @JsonProperty("instagram")
    private String instagram;

}
