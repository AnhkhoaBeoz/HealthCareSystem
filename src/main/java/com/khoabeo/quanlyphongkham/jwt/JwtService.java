package com.khoabeo.quanlyphongkham.jwt;

import com.khoabeo.quanlyphongkham.Constants.AppConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    // Generate JWT token

    public String generateToken(String username, List<String> roles) {
        // Extract username from authentication
//        String username = authentication.getName();

        // Get the current date
        Date currentDate = new Date();

        // Calculate the expiration date based on the current date and defined expiration time
        Date expireDate = new Date(currentDate.getTime() + AppConstants.JWT_EXPIRATION_TIME);

        // Build the JWT token
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setExpiration(expireDate)
                .setIssuedAt(new Date())
                .signWith(getSignInKey())
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(AppConstants.JWT_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extract username from JWT token
    public String getUsername(String token) {
        // Decode the token
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(AppConstants.JWT_SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        // Extract and return the username from the token's claims
        String userName = claims.getSubject();
        return userName;
    }

    // Validate the integrity and expiration of the JWT token
    public static boolean validateToken(String token) {
        try {
            // Parse and verify the token's signature and claims
            Jwts.parserBuilder()
                    .setSigningKey(AppConstants.JWT_SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

            // Token is valid; additional checks (e.g., expiration time) can be added here
            return true;
        } catch (Exception e) {
            // Authentication failed; the token is invalid
            return false;
        }
    }
}
