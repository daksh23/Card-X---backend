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
public class CardDesigns {

    @JsonProperty("design_id")
    private Long cardDesignId;

    @JsonProperty("designName")
    private String cardDesignName;

    @JsonProperty("image")
    private String cardDesignImage;

    @JsonProperty("collection")
    private String cardDesignCollection;

    @JsonProperty("dmyUserName")
    private String dmyUserName;

    @JsonProperty("designAmount")
    private String cardDesignAmount;

}
