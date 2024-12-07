package com.cardx.Cardx.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExecutePaymentRequest {

    private String paymentId;
    private String payerId;
    private String userEmail;
    private String orderId;
    private CardDesigns cardDesigns;

}
