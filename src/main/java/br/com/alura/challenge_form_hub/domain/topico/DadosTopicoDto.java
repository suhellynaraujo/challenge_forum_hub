package com.aluraone.forumHub.domain.topico;

import com.aluraone.forumHub.domain.curso.DadosListagemCursoDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosTopicoDto(

        @NotBlank(message = "Campo titulo é obrigatorio")
        String titulo,
        @NotBlank(message = "Campo mensagem é obrigatorio")
        String mensagem,
        DadosListagemCursoDto curso){}
