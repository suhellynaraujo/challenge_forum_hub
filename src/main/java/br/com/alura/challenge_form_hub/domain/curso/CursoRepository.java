package br.com.alura.challenge_form_hub.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    //para mostrar um curso ativo em especifico dado o id
    Optional<Curso> findByIdAndStatusTrue(Long id);

    //para mostrar todos los cursos ativos
    Page<Curso> findByStatusTrue(Pageable pageable);

}
