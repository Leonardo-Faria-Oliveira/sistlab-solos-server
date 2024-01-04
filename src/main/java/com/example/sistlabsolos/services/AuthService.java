package com.example.sistlabsolos.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.sistlabsolos.interfaces.account.IAccount;

@Service
public class AuthService  {

    @Value("${api.security.secret}")
    private String secret;

    public String generateToken(IAccount account) throws Exception{

        try {

            Algorithm alg = Algorithm.HMAC256(this.secret);
            String token = JWT.create()
            .withIssuer("sistlabsolos")
            .withSubject(account.getEmail())
            .withExpiresAt(LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00")))
            .sign(alg);
            return token;
            
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generating token", e);
        }

    }

    public String validateToken(String token) throws Exception{

        try {

            Algorithm alg = Algorithm.HMAC256(this.secret);
            return JWT.require(alg)
            .withIssuer("sistlabsolos")
            .build()
            .verify(token)
            .getSubject();
            
        } catch (JWTCreationException e) {
            throw new RuntimeException("Invalid token", e);
        }


    }



}
