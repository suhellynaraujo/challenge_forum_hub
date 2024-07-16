package br.com.alura.challenge_form_hub.domain.usuarios;

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
