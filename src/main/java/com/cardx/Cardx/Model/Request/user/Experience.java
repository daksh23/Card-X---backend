package com.cardx.Cardx.Model.Request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Experience {

    private String job;

    private Boolean current;

    private String exStart;

    private String exEnd;

    private String companyName;

}
