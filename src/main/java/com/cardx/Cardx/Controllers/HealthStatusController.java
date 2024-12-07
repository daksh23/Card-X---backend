package com.cardx.Cardx.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200") // Specify the allowed origin
@RestController
@RequestMapping("cardx/rest/v1")
public class HealthStatusController {

    // Health status endpoint
    @GetMapping("/health/status")
    public ResponseEntity health(){
        return ResponseEntity.ok().build();
    }

}
