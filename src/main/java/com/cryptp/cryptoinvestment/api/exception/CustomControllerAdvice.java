package com.cryptp.cryptoinvestment.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(value= {CryptoSymbolException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
