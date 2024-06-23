package com.cardx.Cardx.Model.Request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class userDetails {

    private PersonalInfo personalInfo;

    private List<Education> educations;

    private  List<Experience> experiences;

}
