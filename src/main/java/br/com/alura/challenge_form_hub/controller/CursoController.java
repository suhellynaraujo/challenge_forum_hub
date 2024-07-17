package br.com.alura.challenge_form_hub.controller;

import br.com.alura.challenge_form_hub.domain.curso.DadosCursoDto;
import br.com.alura.challenge_form_hub.domain.curso.DadosListagemCursoDto;
import br.com.alura.challenge_form_hub.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoService service;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCursoDto cursoDto, UriComponentsBuilder uriBuilder){
       DadosListagemCursoDto dto = service.cadastrarCurso(cursoDto);
        Long cursoId = dto.id();
        var uri = uriBuilder.path("/cursos/{id}")
                  .buildAndExpand(cursoId)
                  .toUri();
        return ResponseEntity.created(uri).body(dto);
       // return ResponseEntity.created(uri).body("Curso com Id: " + cursoId + " registrado com sucesso");
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCursoDto>> listar(@PageableDefault(size = 10,sort= {"id"}) Pageable paginacao) {
            var pages = service.listarCursos(paginacao);
            return ResponseEntity.ok(pages);   }

 @PutMapping("/{id}")
 @Transactional
 public ResponseEntity<DadosListagemCursoDto> atualizarCurso(@PathVariable Long id, @RequestBody @Valid DadosCursoDto cursoDto){
     System.out.println("llego al controles, id para act: " + id);
        var cursoAtualizado = service.atualizarCurso(id, cursoDto);
        return ResponseEntity.ok()
                .body(cursoAtualizado);
    }





    //exclusão fisica
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
         service.inativarCurso(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity mostrarDetalhesCurso(@PathVariable Long id) {
        DadosListagemCursoDto cursoDto =  service.detalharCurso(id);
        return ResponseEntity.ok().body(cursoDto);
    }

}