package com.codingshuttle.youtube.hospitalManagement.util;

import com.codingshuttle.youtube.hospitalManagement.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;




    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userid", user.getId().toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(10, ChronoUnit.MINUTES)))
                .signWith(getSecretKey())
                .compact();
    }

    public String getUsernameFromToken(String jwtToken) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey()).build()
                .parseSignedClaims(jwtToken).getPayload();
        return claims.getSubject();
    }



}
