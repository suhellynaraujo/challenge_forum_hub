package com.aluraone.forumHub.service;

import com.aluraone.forumHub.domain.curso.Curso;
import com.aluraone.forumHub.domain.curso.CursoRepository;
import com.aluraone.forumHub.domain.curso.DadosCursoDto;
import com.aluraone.forumHub.domain.curso.DadosListagemCursoDto;
import com.aluraone.forumHub.domain.topico.Topico;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;


    @Transactional
    public DadosListagemCursoDto cadastrarCurso(DadosCursoDto cursoDto) {
        // Validando os campos do Dto
        if (cursoDto.nome() == null ) {
            throw new IllegalArgumentException("O campo Nome é obrigatorio para cadastrar um curso");
        }

        // Criando a entidade desde o DTO
        Curso curso = new Curso(cursoDto);

        // chamando o repository para salvar o curso
        Curso cursoSalvado = repository.save(curso);
        return new DadosListagemCursoDto(cursoSalvado); //.getId();
    }

    public Page<DadosListagemCursoDto> listarCursos(Pageable pageable) {
        Page<Curso> page = repository.findByStatusTrue(pageable);
        return page.map(c -> new DadosListagemCursoDto(
                c.getId(), c.getNome(), c.isStatus()));
    }

    @Transactional
    public DadosListagemCursoDto atualizarCurso(Long idCurso, DadosCursoDto cursoDto) {
        Optional<Curso> optional = repository.findById(idCurso);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Não existe curso com Id: " + idCurso);
        }
        Curso curso = optional.get();
        curso.setNome(cursoDto.nome());
        Curso cursoAct = repository.save(curso);
        return new DadosListagemCursoDto(cursoAct);
    }

    //mostrar detalhes de um curso
    public DadosListagemCursoDto detalharCurso(Long idCurso) {

        Curso curso = repository.findById(idCurso)
                .orElseThrow( () -> new IllegalArgumentException("Curso não encontrado, favor conferir o id"));

          return new DadosListagemCursoDto(curso);
    }


    //eliminação logica
    @Transactional
    public void inativarCurso(Long idCurso) {
        Optional<Curso> optional = repository.findById(idCurso);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Não existe curso com Id: " + idCurso);
        }

        /*
          Curso curso = repository.findById(idCurso)
                .orElseThrow(() -> new IllegalStateException("Não existe Curso com Id: " + idCurso));
          */
        Curso curso = optional.get();
        curso.setStatus(false);
        repository.save(curso);
    }



    public boolean existsById(Long cursoId) {
        return repository.existsById(cursoId);
    }
}
