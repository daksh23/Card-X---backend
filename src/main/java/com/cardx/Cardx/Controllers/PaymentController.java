package com.cardx.Cardx.Controllers;

import com.cardx.Cardx.Model.Request.ExecutePaymentRequest;
import com.cardx.Cardx.Model.Request.user.OrderList;
import com.cardx.Cardx.Services.PaypalPaymentService;
import com.paypal.api.payments.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("cardx/rest/v1/payment")
public class PaymentController {

    @Autowired
    PaypalPaymentService paypalPaymentService;

    @PostMapping("/create")
    public ResponseEntity<String>  createPayment(@RequestBody Map<String, Object> paymentDetails) {
        return paypalPaymentService.createPayment(paymentDetails);
    }

    @PostMapping("/execute")
    public Payment executePayment(@RequestBody ExecutePaymentRequest request) {
        return paypalPaymentService.executePayment(request);
    }
}
