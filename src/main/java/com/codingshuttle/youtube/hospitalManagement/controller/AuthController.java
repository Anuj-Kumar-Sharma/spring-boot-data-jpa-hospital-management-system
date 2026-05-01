package com.codingshuttle.youtube.hospitalManagement.controller;

import com.codingshuttle.youtube.hospitalManagement.entity.LoginRequest;
import com.codingshuttle.youtube.hospitalManagement.entity.LoginResponse;
import com.codingshuttle.youtube.hospitalManagement.entity.SignUpResponse;
import com.codingshuttle.youtube.hospitalManagement.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@NonNull @RequestBody
                                               LoginRequest loginRequest){

        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.signup(loginRequest));
    }
}
