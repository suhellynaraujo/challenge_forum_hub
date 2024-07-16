package br.com.alura.challenge_form_hub.topico;

import br.com.alura.challenge_form_hub.curso.DadosListagemCursoDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosTopicoDto(

        @NotBlank(message = "Campo titulo é obrigatorio")
        String titulo,
        @NotBlank(message = "Campo mensagem é obrigatorio")
        String mensagem,
        DadosListagemCursoDto curso){}

