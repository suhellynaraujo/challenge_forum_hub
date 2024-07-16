package com.aluraone.forumHub.infra.security;

import com.aluraone.forumHub.domain.usuario.Usuario;
import com.aluraone.forumHub.infra.exceptions.AuthorizationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.forumhub.security.token.secret}")
    private String secret;



    public String gerarToken(Usuario usuario){
        try {
            System.out.println(secret);
            var algoritmo = Algorithm.HMAC256(secret);
            //o algoritmo recebe uma senha secreta
           return JWT.create()
                    .withIssuer("API ForumHub") //identifica a API responsavel pela geração de token
                    .withSubject(usuario.getEmail())//dizendo quiem é o usuario a que pertence esse token
                   // .withClaim("id", usuario.getId()) //assim passo cualquer informação
                   //pode chamar ele varias vesses, arquivo chave:valor
                   .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token", exception);
        }
    }



    public String getSubjectFromValidTokenJWT(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            //o algoritmo recebe a senha secreta
            return JWT.require(algoritmo)
                    .withIssuer("API ForumHub") //identificando a API responsavel pela geração de token
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new AuthorizationException("Token JWT inválido ou expirado!");
        }
    }


    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
