package com.cardx.Cardx.Model.Request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

    private String password;

    @JsonProperty("confirmPassword")
    private String recoveryPassword;

}
