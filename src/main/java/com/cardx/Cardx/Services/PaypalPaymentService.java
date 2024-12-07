package com.cardx.Cardx.Services;

import com.cardx.Cardx.DAO.Repository;
import com.cardx.Cardx.Model.Request.CardDesigns;
import com.cardx.Cardx.Model.Request.ExecutePaymentRequest;
import com.cardx.Cardx.Model.Request.user.OrderList;
import com.cardx.Cardx.Model.Request.user.User;
import com.cardx.Cardx.Model.Request.user.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PaypalPaymentService {

    @Autowired
    private APIContext apiContext;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    Repository repository;

    @Autowired
    RowMapperService rowMapperService;

    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = LogManager.getLogger(PaypalPaymentService.class);

    private final ObjectMapper mapper = new ObjectMapper();

    public PaypalPaymentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create Payment Object and return Approval URL
    public ResponseEntity<String> createPayment(Map<String, Object> paymentDetails) {
        logger.debug("Method= createPayment :: paymenDetails :: " + paymentDetails);

        Amount amount = new Amount();
        amount.setCurrency("CAD");
        amount.setTotal(paymentDetails.get("total").toString());

        Transaction transaction = new Transaction();
        transaction.setDescription("Payment Description");
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:4200/not-found");
        redirectUrls.setReturnUrl("http://localhost:4200/not-found");
        payment.setRedirectUrls(redirectUrls);

        logger.debug("Method=createPayment :: payment object :: " + payment);
        String response = "";
        try {
            Payment createdPayment = payment.create(apiContext);

            logger.debug("Method=createPayment :: createdPayment object :: " + createdPayment);

            String approvedUrl = createdPayment.getLinks().stream()
                    .filter(link -> link.getRel().equals("approval_url"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Approval URL not found"))
                    .getHref();

            response = "{\"approvalUrl\":\"" + approvedUrl + "\"}";
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    public Payment executePayment(ExecutePaymentRequest request) {
        logger.debug("Method= executePayment :: Payment Id :: " + request.getPaymentId() + ":: PayerId ::" + request.getPayerId() + ":: Order Id::" + request.getOrderId());

        try {
            Payment payment = new Payment();
            payment.setId(request.getPaymentId());

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(request.getPayerId());

            Payment paymentResponse = payment.execute(apiContext, paymentExecution);
            logger.debug("Method= executePayment :: paymentResponse  :: " + paymentResponse);

            if(paymentResponse != null){
                // Update in Database
                String  payment_details = mapper.writeValueAsString(paymentResponse);

                logger.debug("Method= executePayment :: After json created :: payment_details  :: " + payment_details);

                boolean ans = updateOrderTable(request.getOrderId(), request.getPayerId(), request.getPaymentId(),
                            payment_details, request.getCardDesigns(), request.getUserEmail());

                if(ans) {
                    return paymentResponse;
                }
            }

            return null;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean updateOrderTable(String order_id, String payer_id, String payment_id, String payment_details, CardDesigns cardDesign, String email){
        logger.debug("Method = updateOrderTable :: entered");
        UserDetails userDetails = null;
        String sql = repository.getUserByEmail();
        User user = null;
        String userDetailsJson = null;
        OrderList order = new OrderList();

        // Map user details from database based on email user typed
        try {
            // Query for user details by email
            user = jdbcTemplate.queryForObject(sql, new Object[]{email}, rowMapperService.rowUser);

            userDetailsJson = user.getUser();

            String timeDate = commonUtil.getCurrentDateTime();
            userDetails = mapper.readValue(userDetailsJson, UserDetails.class);

            // Update Order into OrderList
            if(userDetails != null){

                order.setPaymentId(payment_id);
                order.setPayerId(payer_id);
                order.setOrderId(order_id);
                order.setPaymentDetails(payment_details);
                order.setCardDesigns(cardDesign);
                order.setDateTime(timeDate);

                if(!userDetails.getOrderList().isEmpty()){
                     userDetails.getOrderList().add(order);
                }else{
                    userDetails.setOrderList(Collections.singletonList(order));
                }
            }

            userDetailsJson = mapper.writeValueAsString(userDetails);
            String sqlUpdateJson = repository.updateUserJson(); // updated user details with new password

            int rowsAffected = jdbcTemplate.update(sqlUpdateJson, userDetailsJson, email);

            if(rowsAffected > 0){
                return true;
            }else{
                return false;
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}