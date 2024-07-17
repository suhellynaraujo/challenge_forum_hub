package br.com.alura.challenge_form_hub.domain.topico.validacoes;

import com.aluraone.forumHub.domain.ValidacaoException;
import com.aluraone.forumHub.domain.topico.DadosCadastroTopicoDto;
import com.aluraone.forumHub.domain.topico.DadosTopicoDto;
import com.aluraone.forumHub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarCadastroDadosNaoDuplicados implements ValidadorTopicos{

    @Autowired
    TopicoRepository repository;
    @Override
    public void validar(String titulo, String mensagem) {


        var existe = repository.existsByTituloAndMensagem(titulo, mensagem);
        if (existe){
            throw new ValidacaoException("Já existe topico com esse titulo e mensagem");
        }

    }
}
