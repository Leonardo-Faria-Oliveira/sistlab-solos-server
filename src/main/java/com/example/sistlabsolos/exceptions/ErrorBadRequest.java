package com.example.sistlabsolos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class ErrorBadRequest extends RuntimeException {
    public ErrorBadRequest(String message){
        super(message);
    }
}
