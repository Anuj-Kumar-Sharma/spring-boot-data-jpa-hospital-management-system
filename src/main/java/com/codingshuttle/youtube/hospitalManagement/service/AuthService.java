package com.codingshuttle.youtube.hospitalManagement.service;

import com.codingshuttle.youtube.hospitalManagement.entity.LoginRequest;
import com.codingshuttle.youtube.hospitalManagement.entity.LoginResponse;
import com.codingshuttle.youtube.hospitalManagement.entity.SignUpResponse;
import com.codingshuttle.youtube.hospitalManagement.entity.User;
import com.codingshuttle.youtube.hospitalManagement.repository.UserRepository;
import com.codingshuttle.youtube.hospitalManagement.util.AuthUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(@NonNull LoginRequest loginRequest) {
       Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

      User user = (User) authentication.getPrincipal();
      String authToken = authUtil.generateAccessToken(user);
      LoginResponse loginResponse = LoginResponse.builder()
                .userid(user.getId().toString())
                .jwt(authToken)
                .build();
        return loginResponse;

    }

    public SignUpResponse signup(LoginRequest loginRequest) {
    User user = userRepository.findUsersByUsername(loginRequest.username()).orElse(null);
    if(user !=null) throw new IllegalArgumentException("user already exists");

    user = userRepository.save(User.builder().username(loginRequest.username())
            .password(passwordEncoder.encode(loginRequest.password())).build());

    return SignUpResponse.builder().username(user.getUsername()).id(user.getId()).build();

    }
}
