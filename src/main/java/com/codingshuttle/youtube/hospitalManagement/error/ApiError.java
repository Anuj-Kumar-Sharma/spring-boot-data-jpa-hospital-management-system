package com.codingshuttle.youtube.hospitalManagement.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public record ApiError (HttpStatus status, String message, String route, Date date){}

