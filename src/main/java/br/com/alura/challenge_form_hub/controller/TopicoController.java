package com.aluraone.forumHub.controller;

import com.aluraone.forumHub.domain.topico.*;
import com.aluraone.forumHub.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")

public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopicoDto topicoDto, UriComponentsBuilder uriBuilder,  Authentication authentication){

        String emailUsuarioLoggeado = authentication.getName();
        Long topicoId =  topicoService.cadastrarTopico(topicoDto, emailUsuarioLoggeado);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicoId).toUri();
        return ResponseEntity.created(uri).body("Tópico registrado com sucesso. Id: " + topicoId);
    }


    //só os topicos com status = true
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopicoDto>> listar(@PageableDefault(size=10, sort = {"titulo"}) Pageable paginacao){
        var page = topicoService.listarTopicos(paginacao);
         return ResponseEntity.ok(page);
    }



    @PutMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DadosListagemTopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid DadosTopicoDto topicoDto ){
       DadosListagemTopicoDto topicoActDto =  topicoService.atualizarTopico(id, topicoDto);
        return ResponseEntity.ok().body(topicoActDto);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DadosListagemTopicoDto> mostrarTopico(@PathVariable Long id){
        DadosListagemTopicoDto topicoDto = topicoService.mostrarDetalhesTopico(id);
        return ResponseEntity.ok().body(topicoDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity excluir(@PathVariable Long id){
        topicoService.inativarTopico(id);
        return ResponseEntity.noContent().build();
    }


}
