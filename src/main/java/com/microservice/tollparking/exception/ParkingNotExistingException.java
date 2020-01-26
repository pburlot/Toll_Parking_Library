package com.microservice.tollparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParkingNotExistingException extends RuntimeException {

    public ParkingNotExistingException(String s) {
        super(s);
    }
}
