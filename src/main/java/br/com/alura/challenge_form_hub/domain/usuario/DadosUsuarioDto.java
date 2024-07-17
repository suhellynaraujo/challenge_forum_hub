package br.com.alura.challenge_form_hub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosUsuarioDto(
        @NotBlank String nome,
        @NotBlank String senha
) {

    public DadosUsuarioDto(com.aluraone.forumHub.domain.usuario.Usuario usuario){
        this(usuario.getNome(), usuario.getEmail());
    }
}
