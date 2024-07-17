package br.com.alura.challenge_form_hub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosListagemCursoDto( Long id,
                                    String nome,
                                    boolean status) {

    //constructor para mostrar o DTO a partir de un objeto entidad
    public DadosListagemCursoDto(Curso curso){

        this(curso.getId(), curso.getNome(), curso.isStatus());
    }


}



