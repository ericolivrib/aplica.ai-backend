package br.com.coderico.aplicai.service;

import br.com.coderico.aplicai.security.UserAuthenticated;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    private static final String ISSUER_CLAIM = "aplicai.api";

    private final Algorithm algorithm;

    public JwtService(@Value("${api.security.jwt.secret}") String jwtSecret) {
        algorithm = Algorithm.HMAC256(jwtSecret);
    }

    public String generateToken(UserAuthenticated user) {
        Instant expiresAt = Instant.now().plus(8, ChronoUnit.HOURS);
        Instant issuedAt = Instant.now();

        return JWT.create()
                .withExpiresAt(Date.from(expiresAt))
                .withIssuedAt(issuedAt)
                .withIssuer(ISSUER_CLAIM)
                .withSubject(user.getUsername())
                .sign(algorithm);
    }

    public DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER_CLAIM)
                .build();
        return verifier.verify(token);
    }

    public Instant getTokenExpiration(String token) {
        DecodedJWT jwt = verifyToken(token);
        return jwt.getExpiresAtAsInstant();
    }

    public String getSubject(String token) {
        DecodedJWT jwt = verifyToken(token);
        return jwt.getSubject();
    }
}
