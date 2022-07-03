package com.example.testwork.config;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FormatterFromLocalDateTimeToString {

    public String formatter(LocalDateTime localDateTime) {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MM dd, uuu HH:mm:ss");
        return dTF.format(localDateTime);
    }
}
