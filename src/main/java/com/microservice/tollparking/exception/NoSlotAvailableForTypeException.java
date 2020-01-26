package com.microservice.tollparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class NoSlotAvailableForTypeException extends RuntimeException {

    public NoSlotAvailableForTypeException(String s) {
        super(s);
    }
}
