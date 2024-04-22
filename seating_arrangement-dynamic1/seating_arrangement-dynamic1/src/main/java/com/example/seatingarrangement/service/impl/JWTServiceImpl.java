package com.example.seatingarrangement.service.impl;

import com.example.seatingarrangement.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTServiceImpl implements JWTService {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @Override
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    @Override
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return null;
    }

    public Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String generateToken(String companyName, String sessionId) {
        Map<String, String> claims = new HashMap<>();
        claims.put("companyId", companyName);
        claims.put("sessionId", sessionId);
        return createToken(claims, companyName);
    }

    private String createToken(Map<String, String> claims, String companyName) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(companyName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    @Override
    public String generateRefreshToken(String companyName, String sessionId) {
        Map<String, String> claims = new HashMap<>();
        claims.put("companyId", companyName);
        claims.put("sessionId", sessionId);
        return createRefreshToken(claims, companyName);
    }

    private String createRefreshToken(Map<String, String> claims, String companyName) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(companyName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public String extractSubscriptionId(String token) {
        return null;
    }

    private Key getSignKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
