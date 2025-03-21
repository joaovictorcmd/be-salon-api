package com.joaovictorcmd.besalonapi.security.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.joaovictorcmd.besalonapi.security.userdetails.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author joaovictorcmd
 * @date 2025 Mar 21
 */
@Service
public class JwtTokenService {

    @Value("${jwt.secret-key}")
    private static String secretKey;

    @Value("${jwt.issuer}")
    private static String issuer;

    public String generateToken(UserDetailsImpl user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating token:", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Invalid or expired token");
        }
    }

    public Instant creationDate() {
        return Instant.now();
    }

    public Instant expirationDate() {
        return Instant.now().plus(4, ChronoUnit.HOURS);
    }
}
