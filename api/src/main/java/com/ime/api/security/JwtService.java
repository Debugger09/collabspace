package com.ime.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.time.Instant;

@Component
public class JwtService {

    // Clé secrète 256 bits (32 caractères minimum)
    private final String secretKey = "MaCleSecreteTresLonguePourJWT256Bits1234"; 

    private final Duration expiration = Duration.ofMinutes(360);

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(expiration)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isValid(String token) {
        try {
            return !extractExpiration(token).before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    private Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}
