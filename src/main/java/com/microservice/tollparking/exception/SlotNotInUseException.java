package com.microservice.tollparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class SlotNotInUseException extends RuntimeException {

    public SlotNotInUseException(String s) {
        super(s);
    }
}
