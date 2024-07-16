package com.aluraone.forumHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByStatusTrue(Pageable paginacao);
    Optional<Topico> findByIdAndStatusTrue(Long id);

   /* @Query("select t from topicos t where t.titulo = :titulo AND t.mensagem = :mensagem")
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);*/

    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}


