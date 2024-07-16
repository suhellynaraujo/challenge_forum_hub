package br.com.alura.challenge_form_hub.topico.validacoes;

import br.com.alura.challenge_form_hub.domain.usuarios.ValidacaoException;
import br.com.alura.challenge_form_hub.topico.TopicoRepository;
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
