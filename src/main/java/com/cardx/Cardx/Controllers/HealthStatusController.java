package com.cardx.Cardx.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200") // Specify the allowed origin
@RestController
@RequestMapping("cardx/rest/v1/health")
public class HealthStatusController {

    // Health status endpoint
    @GetMapping("/status")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok().body(" Card-x backend service is working as expected....!!!!");
    }

}
