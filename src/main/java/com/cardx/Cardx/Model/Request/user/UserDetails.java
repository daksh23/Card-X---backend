package com.cardx.Cardx.Model.Request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    @JsonProperty("personalInfo")
    private PersonalInfo personalInfo;

    @JsonProperty("education")
    private List<Education> educations;

    @JsonProperty("experience")
    private  List<Experience> experiences;

    @JsonProperty("socialMedia")
    private SocialMedia socialMedia;

    @JsonProperty("orderList")
    private List<OrderList> orderList;

}
