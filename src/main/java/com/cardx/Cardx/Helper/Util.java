package com.cardx.Cardx.Helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class Util {

    private static final Logger logger = LogManager.getLogger(Util.class);

    public String extractUserNameFromEmailForProfileImage(String email) {
        String method = "extractUserNameFromEmailForProfileImage";

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        String[] parts = email.split("@");
        logger.debug(method + ": Left Part Of Email: {}", parts[0] );
        return parts[0];
    }


    public String generateUUID() {
        return UUID.randomUUID().toString();
    }


    public String getCurrentDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }
}
