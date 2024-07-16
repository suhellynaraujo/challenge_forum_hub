package br.com.alura.challenge_form_hub.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosUsuarioDto(
        @NotBlank String nome,
        @NotBlank String senha
) {

    public DadosUsuarioDto(Usuario usuario){
        this(usuario.getNome(), usuario.getEmail());
    }
}
