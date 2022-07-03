package com.example.testwork.exeption;

import org.springframework.http.HttpStatus;

public class ErrorDetails {
    private final HttpStatus httpStatus;
    private final String message;
    private final String details;

    public ErrorDetails(HttpStatus httpStatus, String message, String details) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
        this.details = details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }
}
