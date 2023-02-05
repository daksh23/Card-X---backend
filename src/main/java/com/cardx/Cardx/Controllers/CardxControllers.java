package com.cardx.Cardx.Controllers;

import com.cardx.Cardx.Model.Response.QuoteApiResponse;
import com.cardx.Cardx.Services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Set Data from api call
    @PostMapping("/user/add")
    public ResponseEntity<String> setUserDetails(@RequestBody String userDetails) throws Exception {
        String user = userDetailsService.setUserDetails(userDetails);
        return ResponseEntity.status(200).body(user);
    }

    @PostMapping("/product/add")
    public ResponseEntity<String> setProduct(@RequestBody String productDetails) throws Exception {
        String product = productService.setProduct(productDetails);
        return ResponseEntity.status(200).body(product);
    }

    @PostMapping("/card/design/add")
    public ResponseEntity<String> addCardDesigns(@RequestBody String cardDesignDetails) throws Exception {
        String design =  cardDesignsService.addCardDesigns(cardDesignDetails);
        return ResponseEntity.status(200).body(design);
    }

    @PostMapping("/socialmedia/add")
    public ResponseEntity<String> socialMedia(@RequestBody String socialMediaDetails) throws Exception {
        String socialMedia =  socialMediaService.addSocialMediaDetails(socialMediaDetails);
        return ResponseEntity.status(200).body(socialMedia);
    }

    @PostMapping("/user/address/add")
    public ResponseEntity<String> addAddress(@RequestBody String addressDetails) throws Exception {
        String address = addressService.addAddress(addressDetails);
        return ResponseEntity.status(200).body(address);
    }
}
