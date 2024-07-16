package com.aluraone.forumHub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCursoDto(
        @NotBlank(message = "Campo nome é obrigatorio")
        String nome
) {
}
