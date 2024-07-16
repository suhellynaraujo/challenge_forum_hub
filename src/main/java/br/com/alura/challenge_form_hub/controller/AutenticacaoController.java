package com.aluraone.forumHub.controller;

import com.aluraone.forumHub.domain.usuario.DadosAutenticacaoDto;
import com.aluraone.forumHub.domain.usuario.Usuario;
import com.aluraone.forumHub.infra.security.DadosTokenJWT;
import com.aluraone.forumHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDto dados){

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var autentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) autentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

    }



}
