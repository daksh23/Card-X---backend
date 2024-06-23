package com.cardx.Cardx.Model.Request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    private String study;

    private Boolean current;

    private String eduStart;

    private String eduEnd;

    private String university;

    private String uniCountry;

    private String uniState;

}
