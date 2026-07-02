package com.codingshuttle.youtube.hospitalManagement.error;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandeler {

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<ApiError> handelUsernameNotFoundException(UsernameNotFoundException ex){
     ApiError apiError=new ApiError(LocalDateTime.now(),"Username not found: User doesnot exist"+ex.getMessage(),HttpStatus.NOT_FOUND);
     return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }


    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<ApiError> handelAuthenticationException(AuthenticationException ex){
        ApiError apiError=new ApiError(LocalDateTime.now(),"Authentication failed: Unauthorized User"+ex.getMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }


    @ExceptionHandler(JwtException.class)
    ResponseEntity<ApiError> handelJwtException(JwtException ex){
        ApiError apiError=new ApiError(LocalDateTime.now(),"Jwt Exception: Invalid Jwt token"+ex.getMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }


    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<ApiError> handelAccessDeniedException(AccessDeniedException ex){
        ApiError apiError=new ApiError(LocalDateTime.now(),"Access denied: Insufficient permission"+ex.getMessage(),HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiError> handelJwtException(Exception ex){
        ApiError apiError=new ApiError(LocalDateTime.now(),"An unexpected error occurs"+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }


}
