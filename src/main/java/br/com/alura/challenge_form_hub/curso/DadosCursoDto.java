package br.com.alura.challenge_form_hub.curso;

import jakarta.validation.constraints.NotBlank;
public record DadosCursoDto(
        @NotBlank(message = "Campo nome é obrigatorio")
        String nome
) {
}
