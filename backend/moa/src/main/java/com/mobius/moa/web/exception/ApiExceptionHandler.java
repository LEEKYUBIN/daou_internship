package com.mobius.moa.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(CategoryNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("error-0001", "No category is found with Category : " + ex.getCategory());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        ApiErrorResponse response = new ApiErrorResponse("error-0002", "Server Error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
