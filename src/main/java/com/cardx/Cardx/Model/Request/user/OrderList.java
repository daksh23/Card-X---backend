package com.cardx.Cardx.Model.Request.user;

import com.cardx.Cardx.Model.Request.CardDesigns;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderList {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("payer_id")
    private String payerId;

    @JsonProperty("payment_id")
    private String paymentId;

    @JsonProperty("payment_details")
    private String paymentDetails;

    @JsonProperty("cardDetails")
    private CardDesigns cardDesigns;

    @JsonProperty("date_time")
    private String dateTime;

    @JsonProperty("email")
    private String userEmail;


}
