package com.cardx.Cardx.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cardx.Cardx.Services.*;

@CrossOrigin(origins = "http://localhost:4200") // Specify the allowed origin
@RestController
@RequestMapping("cardx/rest/v1")
public class CardxSetController {

    @Autowired
    HelpService helpService;

    // Set Data from api call
    @PostMapping("/card/help/add")
    public ResponseEntity<String> addHelp(@RequestBody String helpDetails) throws Exception {
        return helpService.addHelp(helpDetails);
    }
}
