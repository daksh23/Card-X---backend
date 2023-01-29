package com.cardx.Cardx.Model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @JsonProperty("event_id")
    private Long event_id;

    @JsonProperty("stage")
    private String stage;

    @JsonProperty("user_id")
    private Long user_id;

    @JsonProperty("json_data")
    private String json_data;

    @JsonProperty("event_date")
    private String date;


}
