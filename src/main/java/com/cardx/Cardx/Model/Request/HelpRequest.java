package com.cardx.Cardx.Model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HelpRequest {

    @JsonProperty("help_id")
    private Long helpId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("question")
    private String question;

    @JsonProperty("help_image")
    private String image;
}
