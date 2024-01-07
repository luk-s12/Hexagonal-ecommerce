package org.example.hexagonal.ecommerce.application.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import org.example.hexagonal.ecommerce.application.constants.DateConstant;

public class DataTimeService {

    public static Timestamp coverToTimestamp(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( DateConstant.DATE_FORMAT );
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        Timestamp timestamp			= Timestamp.valueOf(localDateTime);
        return timestamp;
    }
    
}
