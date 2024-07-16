package com.aluraone.forumHub.domain.topico.validacoes;

import com.aluraone.forumHub.domain.topico.DadosCadastroTopicoDto;
import com.aluraone.forumHub.domain.topico.DadosTopicoDto;

public interface ValidadorTopicos {
    void validar(String titulo, String mensagem);
}
