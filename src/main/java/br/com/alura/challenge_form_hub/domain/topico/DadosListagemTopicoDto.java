package br.com.alura.challenge_form_hub.domain.topico;

import br.com.alura.challenge_form_hub.domain.curso.DadosListagemCursoDto;
import br.com.alura.challenge_form_hub.domain.usuario.DadosUsuarioDto;

import java.time.LocalDateTime;

public record DadosListagemTopicoDto(
    Long id,
    String titulo,
    String mensagem,
    LocalDateTime dataCriacao,
    boolean status,
    DadosListagemCursoDto curso,
    DadosUsuarioDto autor
){


    public DadosListagemTopicoDto(br.com.alura.challenge_form_hub.domain.topico.Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.isStatus(),
                 new DadosListagemCursoDto(topico.getCurso()), new DadosUsuarioDto(topico.getAutor())
                );
    }
}


