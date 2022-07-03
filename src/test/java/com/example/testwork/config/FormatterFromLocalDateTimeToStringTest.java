package com.example.testwork.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FormatterFromLocalDateTimeToStringTest {
    private FormatterFromLocalDateTimeToString formatterFromLocalDateTimeToString;

    @BeforeEach
    void setUp() {
        this.formatterFromLocalDateTimeToString = new FormatterFromLocalDateTimeToString();
    }

    @AfterEach
    void tearDown() {
        formatterFromLocalDateTimeToString = null;
    }

    @Test
    void formatter() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 6, 20,20, 50, 0);
        assertEquals("06 20, 2022 20:50:00", formatterFromLocalDateTimeToString.formatter(dateTime));
    }
}