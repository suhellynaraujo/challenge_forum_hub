package br.com.alura.challenge_form_hub.service;

import br.com.alura.challenge_form_hub.domain.curso.Curso;
import br.com.alura.challenge_form_hub.domain.curso.CursoRepository;
import br.com.alura.challenge_form_hub.domain.topico.*;
import br.com.alura.challenge_form_hub.domain.topico.validacoes.ValidarCadastroDadosNaoDuplicados;
import br.com.alura.challenge_form_hub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ValidarCadastroDadosNaoDuplicados validador;

    @Transactional
    public Long cadastrarTopico(@RequestBody @Valid DadosCadastroTopicoDto dados, String emailUsuarioLoggeado) {

     //chamando mi clase validadora
       validador.validar(dados.titulo(), dados.mensagem());


        //usuario loggeado
        Usuario usuarioLoggeado = usuarioService.findByEmail(emailUsuarioLoggeado);

        //curso
       Long idCurso = dados.curso().id();
       Optional cursoOpt = cursoRepository.findById(idCurso);

        if (cursoOpt.isPresent()) {
            Curso curso = (Curso) cursoOpt.get();
            Topico topico = new Topico(null, dados.titulo(), dados.mensagem(), LocalDateTime.now(), true, curso, usuarioLoggeado);
            Topico topicoSalvado = topicoRepository.save(topico);
            return topicoSalvado.getId();
        }else{
            throw new IllegalArgumentException("O id do curso não é válido");
        }
    }


    @Transactional
    public void inativarTopico(Long id){

        Topico topico = topicoRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico não encontrado ou desativado previamente"));

        //colocando status como falso e assim desativo o topico
        topico.setStatus(false);

        //atualizando o status no banco
       topicoRepository.save(topico);
    }

    @Transactional
    public DadosListagemTopicoDto atualizarTopico(Long id, DadosTopicoDto dadosTopicoDto){
         Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico não encontrado, favor conferir o id."));

         //validando se ja tem topico com esse titulo e mensagem
        validador.validar(dadosTopicoDto.titulo(), dadosTopicoDto.mensagem());

        //atualizando os dados que vem no Dto
        if(dadosTopicoDto.titulo() != null){
            topico.setTitulo(dadosTopicoDto.titulo());
        }
        if(dadosTopicoDto.mensagem() != null){
            topico.setMensagem(dadosTopicoDto.mensagem());
        }

        //atualizando o topico no banco
       Topico topicoAct = topicoRepository.save(topico);

        //devolvendo o Dto com as novas informaçoes
        return new DadosListagemTopicoDto(topicoAct);
    }

    public DadosListagemTopicoDto mostrarDetalhesTopico(Long id) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Tópico não encontrado, favor conferir o id."));
        return new DadosListagemTopicoDto(topico);
    }

    public Page<DadosListagemTopicoDto> listarTopicos(Pageable paginacao) {
        Page<Topico> pageTopicos = topicoRepository.findByStatusTrue(paginacao);
        return pageTopicos.map(DadosListagemTopicoDto::new);
    }



}
