package com.cardx.Cardx.Services;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CommonUtil {

    public String getCurrentDateTime() {
        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format and return as a String
        return now.format(formatter);
    }

}
