package com.cardx.Cardx.Model.Request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {

    private String userId;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String bio;

    private Address address;

    private String image;

    private Credentials credentials;

}
