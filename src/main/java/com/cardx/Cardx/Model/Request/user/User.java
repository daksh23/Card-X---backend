package com.cardx.Cardx.Model.Request.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String user_id;

    private String username;

    private String email;

    private String user;

    @JsonProperty("time")
    private String timeDate;
}
