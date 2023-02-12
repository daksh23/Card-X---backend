package com.cardx.Cardx.Services;

import com.cardx.Cardx.Model.Request.EmailerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailerService {

    @Autowired
    private JavaMailSender mailSender;

    private final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LogManager.getLogger(EmailerService.class);
    public int sendEmail(String emailData) throws Exception {
        logger.debug("method: sendEmail: {}", emailData);
        MimeMessage message = mailSender.createMimeMessage();
        EmailerRequest email;

        try{
            email = mapper.readValue(emailData, EmailerRequest.class);
            message.setFrom(new InternetAddress("cardx@cardx.com"));
            message.setRecipients(MimeMessage.RecipientType.TO, email.getTo());
            message.setSubject(email.getSubject());
            message.setContent(email.getHtmlBody(), "text/html; charset=utf-8");
            mailSender.send(message);
            logger.debug("Mail Sent: {}", mailSender.toString());
        }catch(Exception e){
            throw new Exception("Error in setCardDesigns");
        }
        return 1;
    }
}
