package com.cardx.Cardx.Controllers;

import com.cardx.Cardx.Model.Response.QuoteApiResponse;
import com.cardx.Cardx.Services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // Specify the allowed origin
@RestController
@RequestMapping("cardx/rest/v1")
public class CardxControllers {

    @Autowired
    GetCardService getCardService;

    @Autowired
    SocialMediaService socialMediaService;

    @Autowired
    QuoteApiService quoteApiService;

    @Autowired
    ProductService productService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CardDesignsService cardDesignsService;

    @Autowired
    AddressService addressService;

    @Autowired
    EmailerService emailerService;

    private ObjectMapper objectMapper = new ObjectMapper();


    // Extra Endpoints including 3rd Party belows:
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok().body("Welcome to Card-X");
    }

    @GetMapping("/quotes")
    public String getQuoteOfTheDay() throws JsonProcessingException {
        QuoteApiResponse quote = quoteApiService.getQuoteOfTheDay();
        return objectMapper.writeValueAsString(quote);
    }

    // Getting Data Apis Endpoint belows
    @GetMapping("user/{id}")
    public ResponseEntity<String> getUserById(@PathVariable Long id) throws JsonProcessingException {
        return getCardService.getUserById(id);
    }

    @GetMapping("/user/ids")
    public List<Long> getAllUserId(){
        return getCardService.getAllUserId();
    }

    @GetMapping("/user/emails")
    public List<String> getAllEmails(){
        return getCardService.getAllEmails();
    }

    @GetMapping("/card/design/{id}")
    public ResponseEntity<String> getCardDesignById(@PathVariable Long id) throws JsonProcessingException {
        return ResponseEntity.ok(cardDesignsService.getCardDesignById(id));
    }

    @GetMapping("/card/designs")
    public ResponseEntity<String> getCardDesign() throws JsonProcessingException {
        return ResponseEntity.ok(cardDesignsService.getCardDesign());
    }

    // Set Data from api call
    @PostMapping("/user/add")
    public ResponseEntity<String> setUserDetails(@RequestBody String userDetails) throws Exception {
        return userDetailsService.setUserDetails(userDetails);
    }

    @PostMapping("/product/add")
    public ResponseEntity<String> setProduct(@RequestBody String productDetails) throws Exception {
        return productService.setProduct(productDetails);
    }

    @PostMapping("/card/design/add")
    public ResponseEntity<String> addCardDesigns(@RequestBody String cardDesignDetails) throws Exception {
        return cardDesignsService.addCardDesigns(cardDesignDetails);
    }

    @PostMapping("/social/media/add")
    public ResponseEntity<String> socialMedia(@RequestBody String socialMediaDetails) throws Exception {
        return socialMediaService.addSocialMediaDetails(socialMediaDetails);
    }

    @PostMapping("/user/address/add")
    public ResponseEntity<String> addAddress(@RequestBody String addressDetails) throws Exception {
        return addressService.addAddress(addressDetails);
    }


    // Mailer Controller
    @PostMapping("/mail/send")
    public ResponseEntity<String> mailerSender(@RequestBody String mailData) throws Exception {
        emailerService.sendEmail(mailData);
        return ResponseEntity.status(200).body("Mail Sent");
    }
}
