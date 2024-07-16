package com.aluraone.forumHub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosResUsuarioDto(
        Long id,

        String nome,

        String email,
        boolean status

) {

    public DadosResUsuarioDto(Long id, String nome, String email) {
        this(id, nome, email, false);
    }



}



