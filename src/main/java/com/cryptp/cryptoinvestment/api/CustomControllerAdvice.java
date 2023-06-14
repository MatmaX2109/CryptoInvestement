package com.cryptp.cryptoinvestment.api;

import com.cryptp.cryptoinvestment.api.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(value= { HttpClientErrorException.BadRequest.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        ErrorDetails errorDetails = ErrorDetails.builder()
                .msg(bodyOfResponse)
                .build();
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
}
