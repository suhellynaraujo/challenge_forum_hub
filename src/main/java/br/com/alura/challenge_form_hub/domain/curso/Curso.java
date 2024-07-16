package br.com.alura.challenge_form_hub.domain.curso;

import br.com.alura.challenge_form_hub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="cursos")
@Table(name="cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")

public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private boolean status;

    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos = new ArrayList<>();

    //para criar o objeto Curso dado o Dto, se usa na hora de enviar pro banco de dados e salvar o registro
    public Curso(DadosCursoDto dto){
        this.nome = dto.nome();
        this.status = true;
    }

}

