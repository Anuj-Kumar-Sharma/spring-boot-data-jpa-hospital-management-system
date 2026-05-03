package com.codingshuttle.youtube.hospitalManagement.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(HttpServletRequest httpServletRequest,
                                                                UsernameNotFoundException usernameNotFoundException){
        Instant instant = Instant.now();
        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND)
                .message(usernameNotFoundException.getMessage())
                .route(httpServletRequest.getRequestURI())
                .date(Date.from(instant))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(HttpServletRequest httpServletRequest,
                                                                Exception exception){
        Instant instant = Instant.now();
        ApiError apiError = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .route(httpServletRequest.getRequestURI())
                .date(Date.from(instant))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


}
