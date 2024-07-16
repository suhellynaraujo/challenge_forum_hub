package br.com.alura.challenge_form_hub.topico;
import br.com.alura.challenge_form_hub.curso.DadosListagemCursoDto;
import br.com.alura.challenge_form_hub.domain.usuarios.DadosUsuarioDto;

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


    public DadosListagemTopicoDto(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.isStatus(),
                new DadosListagemCursoDto(topico.getCurso()), new DadosUsuarioDto(topico.getAutor())
        );
    }
}

