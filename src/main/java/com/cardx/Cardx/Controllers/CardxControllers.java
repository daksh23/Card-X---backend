package com.cardx.Cardx.Controllers;

import com.cardx.Cardx.Model.Request.AuthRequest;
import com.cardx.Cardx.Services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200") // Specify the allowed origin
@RestController
@RequestMapping("cardx/rest/v1")
public class CardxControllers {

    @Autowired
    CardDesignsService cardDesignsService;

    @Autowired
    CardFeaturesService cardFeaturesService;

    @Autowired
    ValidationDataForUIService validationDataForUIService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ProfileService profileService;


    // Extra Endpoints including 3rd Party belows:
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok().body("Welcome to Card-X");
    }

    // Getting Data Apis Endpoint belows
    @GetMapping("/card/designs")
    public ResponseEntity<String> getCardDesign() throws JsonProcessingException {
        return ResponseEntity.ok(cardDesignsService.getCardDesign());
    }

    @GetMapping("/card/features")
    public ResponseEntity<String> getFeatures() throws Exception {
        return ResponseEntity.ok(cardFeaturesService.retrieveFeatures());
    }

    // Get User Data  - emails
    @GetMapping("/user/emails")
    public ResponseEntity<String> getEmails() throws Exception {
        return ResponseEntity.ok(validationDataForUIService.retrieveEmails());
    }

    // Login API
    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) throws Exception {
        return authenticationService.login(authRequest.getEmail(), authRequest.getPassword());
    }

    // Profile retrieve
    @PostMapping("/user/profile")
    public ResponseEntity<String> profile(@RequestBody String email) throws Exception {
        return profileService.retrieveProfileInformation(email);
    }


}
