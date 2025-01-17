package com.cyfrifpro.config.security;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;



@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    /**
     * Generate a JWT token with userId, email, and role.
     *
     * @param id   User's ID
     * @param email    User's email
     * @param role User's role
     * @return Generated JWT token
     * @throws IllegalArgumentException
     * @throws JWTCreationException
     */
    public String generateToken(Long id, String email, String role) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("id", id) // Add id claim
                .withClaim("email", email) // Add email claim
                .withClaim("role", role) // Add role claim
                .withIssuedAt(new Date())
                .withIssuer("CyfrifPro Tech")
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * Validate the JWT token and retrieve claims.
     *
     * @param token JWT token
     * @return Map of claims (e.g., userId, email, roleName)
     * @throws JWTVerificationException
     */
    public Map<String, Object> validateTokenAndRetrieveClaims(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("CyfrifPro Tech").build();

        // Verify the token
        DecodedJWT jwt = verifier.verify(token);

        // Create a map to hold the claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", jwt.getClaim("id").asLong()); // Retrieve id claim
        claims.put("email", jwt.getClaim("email").asString()); // Retrieve email claim
        claims.put("role", jwt.getClaim("role").asString()); // Retrieve roleName claim

        return claims;
    }
}