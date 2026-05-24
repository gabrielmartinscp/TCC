package lad.sys.api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lad.sys.api.dto.projeto.DadosAtualizacaoProjeto;
import lad.sys.api.dto.projeto.DadosCadastroProjeto;
import lombok.*;

@Table(name = "projeto")
@Entity(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_projeto;
    private Long id_cliente;
    private String nome;
    private String tipo_obra;
    private String descricao;
    private String data_inicio;
    private String data_fim;
    private Boolean deletado;


    public Projeto(DadosCadastroProjeto dados) {
        this.id_cliente = dados.id_cliente();
        this.nome = dados.nome();
        this.tipo_obra = dados.tipo_obra();
        this.descricao = dados.descricao();
        this.data_inicio = dados.data_inicio();
        this.data_fim = dados.data_fim();
        this.deletado = false;
    }

    public void atualizarRegistro(@Valid DadosAtualizacaoProjeto dados) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.tipo_obra = dados.tipo_obra() != null ? dados.tipo_obra() : this.tipo_obra;
        this.descricao = dados.descricao() != null ? dados.descricao() : this.descricao;
        this.data_inicio = dados.data_inicio()  != null ? dados.data_inicio() : this.data_inicio;
        this.data_fim = dados.data_fim() != null ? dados.data_fim() : this.data_fim;
    }

    public void deletarProjeto() {
        this.deletado = true;
    }
}
