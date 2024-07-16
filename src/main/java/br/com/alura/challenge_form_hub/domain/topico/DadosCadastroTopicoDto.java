package br.com.alura.challenge_form_hub.domain.topico;

import br.com.alura.challenge_form_hub.domain.curso.DadosListagemCursoDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopicoDto(

        @NotBlank(message = "Campo titulo é obrigatorio")
        String titulo,
        @NotBlank(message = "Campo mensagem é obrigatorio")
        String mensagem,
        @NotNull(message = "Campo id del curso é obrigatorio") DadosListagemCursoDto curso) {
}

