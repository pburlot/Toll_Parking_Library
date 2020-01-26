package com.microservice.tollparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCarTypeException extends RuntimeException {

    public InvalidCarTypeException(String s) {
        super(s);
    }
}
