package br.com.alura.challenge_form_hub.domain.topico;

import br.com.alura.challenge_form_hub.domain.curso.Curso;
import br.com.alura.challenge_form_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity(name="Topico")
@Table(name="topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;

    @Column(name="data_criacao")
    private LocalDateTime dataCriacao;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="curso_id")//nome da coluna na tabela
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="autor_id")//nome da coluna na tabela
    private Usuario autor;

    @Override
    public String toString() {
        return "Topico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", status=" + status +
                ", curso=" + curso +
                ", autor=" + autor +
                '}';
    }


   /* public Topico(DadosTopicoDto topicoDto){

        this.titulo = topicoDto.titulo();
        this.mensagem = topicoDto.mensagem();
        this.status = true;
        System.out.println(LocalDateTime.now());
        this.dataCriacao = LocalDateTime.now();
        this.setCurso(new Curso(topicoDto.curso().id(), topicoDto.curso().nome()));

    }*/

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
