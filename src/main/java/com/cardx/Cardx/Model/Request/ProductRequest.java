package com.cardx.Cardx.Model.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @JsonProperty("product_id")
    private Long product_id;

    @JsonIgnore
    private Long user_id;

    @JsonProperty("card_name")
    private String card_name;

    @JsonProperty("type_card")
    private String type_card;

    @JsonProperty("design_id")
    private String card_design_id;

    @JsonProperty("design_name")
    private String card_design_name;

    @JsonProperty("design_amount")
    private String card_design_amount;
}
