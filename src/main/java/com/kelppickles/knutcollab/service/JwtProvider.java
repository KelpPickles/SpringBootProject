package com.kelppickles.knutcollab.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

    private final SecretKey secretKey;

    public JwtProvider(@Value("${jwt.secret}") String secret) {

        this.secretKey = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String createToken(String email) {
        Date now = new Date();
        Date exp = new Date(
                now.getTime() + 1000 * 60 * 60  // 1시간
        );

        return Jwts.builder()
                .subject(email)         // JWT Payload - sub
                .issuedAt(now)          // 발급
                .expiration(exp)        // 만료 시간
                .signWith(secretKey)    // 서명
                .compact();             // 문자열 형태로 만들기
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
