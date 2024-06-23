package com.cardx.Cardx.Controllers;

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
    CardDesignsService cardDesignsService;

    @Autowired
    CardFeaturesService cardFeaturesService;

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

}
