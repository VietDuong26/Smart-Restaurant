package com.example.SmartRestaurant.config.jwt;

import com.example.SmartRestaurant.config.userdetails.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${access-token.secret-key}")
    String secretToken;
    @Value("${access-token.expiration}")
    Long expiration;
    @Value("${refresh-token.secret-key}")
    String refreshSecretToken;
    @Value("${refresh-token.expiration}")
    Long refreshExpiration;

    //access token
    public String generateAccessToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_"))
                .toList();

        List<String> permissions = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("PERM_"))
                .toList();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles)
                .claim("permissions", permissions)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getAccessSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String extractAccessUsername(String token) {
        return extractAccessAllClaims(token).getSubject();
    }

    public boolean validateAccessToken(String token, UserDetails userDetails) {
        final String username = extractAccessUsername(token);
        return username.equals(userDetails.getUsername()) && !isAccessTokenExpired(token);
    }

    private boolean isAccessTokenExpired(String token) {
        return extractAccessAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAccessAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getAccessSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getAccessSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretToken);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //refresh token
    public String generateRefreshToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshExpiration);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getRefreshSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String extractRefreshUsername(String token) {
        return extractRefreshAllClaims(token).getSubject();
    }

    public boolean validateRefreshToken(String token, UserDetails userDetails) {
        final String username = extractRefreshUsername(token);
        return username.equals(userDetails.getUsername()) && !isRefreshTokenExpired(token);
    }

    private boolean isRefreshTokenExpired(String token) {
        return extractRefreshAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractRefreshAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getRefreshSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getRefreshSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(refreshSecretToken);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}