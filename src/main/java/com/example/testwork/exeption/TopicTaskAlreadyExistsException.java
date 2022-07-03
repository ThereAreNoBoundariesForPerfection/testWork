package com.example.testwork.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TopicTaskAlreadyExistsException extends RuntimeException {

    public TopicTaskAlreadyExistsException(String message) {
        super(message);
    }
}
