package com.example.mongodb.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class Exceptions {


    @ExceptionHandler(NotFoundException.class)
    public ResponseStatusException catchEx(HttpStatusCodeException statusCodeException) {
        return new ResponseStatusException(statusCodeException.getStatusCode());
    }
}
