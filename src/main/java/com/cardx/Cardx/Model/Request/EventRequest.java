package com.cardx.Cardx.Model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @JsonProperty("event_id")
    private Long eventId;

    @JsonProperty("stage")
    private String stage;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("json_data")
    private String jsonData;

    @JsonProperty("event_date")
    private String date;
}
