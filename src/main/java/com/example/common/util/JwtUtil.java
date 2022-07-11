package com.example.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generate(String userSeq, String type) {

        // 1. token 내부에 저장할 정보
        Map<String, Object> claims = new HashMap<>();
        claims.put("userSeq", userSeq);
        
        // 2. token 생성일
        final Date createdDate = new Date();

        // 3. token 만료일
        long expirationTime;
        if ("ACCESS".equals(type)) {
            // 테스트용(30분)
            expirationTime = Long.parseLong(expiration) * 1000;
        } else {
            // 테스트용(1시간)
            expirationTime = Long.parseLong(expiration) * 1000 * 2;
        }
        final Date expirationDate = new Date(createdDate.getTime() + expirationTime);


        return Jwts.builder()
                .setClaims(claims)      // 1
                .setIssuedAt(createdDate)       // 2
                .setExpiration(expirationDate)      // 3
                .signWith(key)
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserSeqFromToken(String token) {
        return getClaimsFromToken(token).get("userSeq", String.class);
    }

    private JwtUtil() { }
}
