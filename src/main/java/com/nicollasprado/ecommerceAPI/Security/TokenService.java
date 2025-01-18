package com.nicollasprado.ecommerceAPI.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nicollasprado.ecommerceAPI.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ecommerce_API") // quem criou o token
                    .withSubject(user.getUsername()) // para quem sera o token
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException err) {
            throw new RuntimeException("Erro ao gerar token: ", err);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ecommerce_API")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException err){
            return ""; // O spring security retorna unauthorized automaticamente ao perceber que recebeu uma string vazia
        }
    }

    private Instant genExpirationDate(){
        // Horario da criaçao + 2 horas com base no offset -3h
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
