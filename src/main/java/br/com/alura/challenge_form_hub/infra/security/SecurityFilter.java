package com.aluraone.forumHub.infra.security;

import com.aluraone.forumHub.domain.usuario.UsuarioRepository;
import com.aluraone.forumHub.infra.exceptions.AuthorizationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository repository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AuthorizationException {
        var tokenJWT = recuperarTokenDoCabecalho(request);
        System.out.println(tokenJWT);
        if(tokenJWT != null){
            var subject = tokenService.getSubjectFromValidTokenJWT(tokenJWT);
            System.out.println(subject);
            var usuario = repository.findByEmail(subject);
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

           //falando para o Spring que o usuario está autentificado, ele não sabe pq é a API é Stateless
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarTokenDoCabecalho(HttpServletRequest request) {
       var authorizationHeader = request.getHeader("Authorization");


       if(authorizationHeader != null){
           return authorizationHeader.replace("Bearer ","");//tem espaço apos a palavra Bearer
       }
       return null;
    }
}
