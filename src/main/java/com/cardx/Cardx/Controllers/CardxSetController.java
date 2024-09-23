package com.cardx.Cardx.Controllers;

import com.cardx.Cardx.Model.Request.ChangePasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cardx.Cardx.Services.*;

/*
    * Class: SetController : set data to database
*/
@CrossOrigin(origins = "http://localhost:4200") // Specify the allowed origin
@RestController
@RequestMapping("cardx/rest/v1")
public class CardxSetController {

    @Autowired
    HelpService helpService;

    @Autowired
    RegisterUserService registerUserService;

    @Autowired
    ChangePasswordService changePasswordService;

    @PostMapping("/card/help/add")
    public ResponseEntity<String> addHelp(@RequestBody String helpDetails) throws Exception {
        return helpService.addHelp(helpDetails);
    }

    @PostMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody String userDetails) throws Exception {
        return registerUserService.addUser(userDetails);
    }

    @PostMapping("/user/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
        return changePasswordService.changePassword(changePasswordRequest.getCurrentPassword(), changePasswordRequest.getNewPassword(), changePasswordRequest.getEmail());
    }
}
