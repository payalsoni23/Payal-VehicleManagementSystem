package com.AA.VehicleManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidVehicleException.class, HttpMessageConversionException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadRequest(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    /*@ExceptionHandler(value
            = {InvalidVehicleException.class, HttpMessageConversionException.class})
    protected ResponseEntity<Object> handleNotFoundError(RuntimeException runtimeException, WebRequest webRequest) {
        var bodyOfResponse = runtimeException.getMessage();
        return handleExceptionInternal(runtimeException, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
        if (runtimeException instanceof InvalidVehicleException exception) {
            return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
        } else if (runtimeException instanceof HttpMessageConversionException exception) {
            return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
        } else {
            return handleExceptionInternal(runtimeException, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
        }
}

    @ExceptionHandler(value
            = {HttpMessageNotReadableException.class})
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException runtimeException, WebRequest webRequest) {
        var bodyOfResponse = runtimeException.getMessage();
        return handleExceptionInternal(runtimeException, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }*/
}

