package com.codingshuttle.youtube.hospitalManagement.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private LocalDateTime timestamp;
    private String error;
    private HttpStatus statusCode;

//    public ApiError(String error, HttpStatus statusCode) {
//        this.timestamp = LocalDateTime.now();
//        this.error = error;
//        this.statusCode = statusCode;
//    }
}
