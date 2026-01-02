package com.auth.App.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    private static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    public static String createToken(Claims claims) {
        var key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        var now = new Date();
        var exp = new Date(now.getTime() + 3600_000); // 1 hour

        return Jwts.builder()
                   .subject(claims.getSubject())
                   .issuedAt(now)
                   .expiration(exp)
                   .signWith(key)
                   .compact();
    }

    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String tokenUsername = getUsername(token);
        return (tokenUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        var claims = extractAllClaim(token);
        return claimsResolver.apply((Claims) claims);
    }

    public static Map<String, Object> extractAllClaim(String token) {
        var key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getPayload();
    }
}
