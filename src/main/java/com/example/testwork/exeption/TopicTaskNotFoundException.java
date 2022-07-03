package com.example.testwork.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TopicTaskNotFoundException extends RuntimeException {

    public TopicTaskNotFoundException(String message) {
        super(message);
    }
}
