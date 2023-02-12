package com.cardx.Cardx.Model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailerRequest {

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("body")
    private String htmlBody;

    @JsonProperty("to")
    private  String to;

}
