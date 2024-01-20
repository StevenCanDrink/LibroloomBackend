      package com.team4.libroloom.service;


import com.team4.libroloom.model.SecurityMember;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
@Getter
@Setter
@RequiredArgsConstructor
public class JwtUtil {
    private SecretKey secretKey;

    public SecretKey getSigningKey(){
        this.secretKey = Jwts.SIG.HS512.key().build();
        return secretKey;
    }


    public String generateToken(SecurityMember securityMember) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, securityMember.getUsername());
    }

    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().claims(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSigningKey())
                .compact();
    }

    public Boolean validateToken(String token, SecurityMember securityMember) {
        final String username = extractUserName(token);
        return (username.equals(securityMember.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    public Date extractExpiration(String token) {
        return extractClaims(token,Claims::getExpiration);
    }

    public
    String extractUserName(String token) {
        return extractClaims(token,Claims::getSubject);
    }


    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Jws<Claims> claims = extractAllClaims(token);
        return claimsResolver.apply(claims.getPayload());
    }

    private Jws<Claims> extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parse(token).accept(Jws.CLAIMS);
        } catch (JwtException ex) {
            throw new JwtException("WRONG JWT"+ex.getMessage(),ex);
        }


    }


}
