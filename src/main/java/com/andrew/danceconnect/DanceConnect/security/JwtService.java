package com.andrew.danceconnect.DanceConnect.security;

import com.andrew.danceconnect.DanceConnect.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
@Slf4j
public class JwtService {
    private static final String SECRET = "super_secret_key_super_secret_key_super_secret_key"; // 256-битный ключ
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 минут
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(Claims claims, UserDetails userDetails) {
        Long userIdFromToken = Long.parseLong(claims.getSubject()); // Извлекаем ID из токена
        Long userIdFromDetails = ((CustomUserDetails) userDetails).getUserId(); // Получаем ID из UserDetails

        Date expiration = claims.getExpiration(); // Извлекаем дату истечения

        return Objects.equals(userIdFromToken, userIdFromDetails) && expiration != null && expiration.after(new Date());
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
