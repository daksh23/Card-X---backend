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
    private String cardDesignId;

    @JsonProperty("design_name")
    private String cardDesignName;

    @JsonProperty("design_amount")
    private String cardDesignAmount;
}
