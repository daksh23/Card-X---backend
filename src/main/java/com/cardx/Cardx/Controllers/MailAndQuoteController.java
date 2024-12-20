package com.cardx.Cardx.Controllers;


import com.cardx.Cardx.Model.Response.QuoteApiResponse;
import com.cardx.Cardx.Services.EmailerService;
import com.cardx.Cardx.Services.QuoteApiService;
import com.cardx.Cardx.Services.UploadProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200") // Specify the allowed origin
@RestController
@RequestMapping("cardx/rest/v1")
public class MailAndQuoteController {

    @Autowired
    EmailerService emailerService;

    @Autowired
    QuoteApiService quoteApiService;

    @Autowired
    UploadProfile uploadProfile;

    private ObjectMapper objectMapper = new ObjectMapper();

    // Mailer Controller
    @PostMapping("/mail/send")
    public ResponseEntity<String> mailerSender(@RequestBody String mailData) throws Exception {
        emailerService.sendEmail(mailData);
        return ResponseEntity.status(200).body("Mail Sent");
    }

    // Quote Controller
    @GetMapping("/quotes")
    public String getQuoteOfTheDay() throws JsonProcessingException {
        QuoteApiResponse quote = quoteApiService.getQuoteOfTheDay();
        return objectMapper.writeValueAsString(quote);
    }

    // Upload Profile Image - User
    @PostMapping(value="/upload")
    public String uploadProfileImage(@RequestParam("file") MultipartFile file, String fileName) throws JsonProcessingException {
        return uploadProfile.uploadProfile(file, fileName);


    }
}
