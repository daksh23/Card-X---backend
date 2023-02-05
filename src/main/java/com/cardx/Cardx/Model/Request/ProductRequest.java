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

    @JsonIgnore
    private Long productId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("card_design_id")
    private Long cardDesignId;

    @JsonProperty("card_name")
    private String cardName;

    @JsonProperty("type_card")
    private String typeCard;





}
