package lad.sys.api.model;

import jakarta.persistence.*;
import lad.sys.api.dto.fornecedor.DadosAtualizacaoFornecedor;
import lad.sys.api.dto.fornecedor.DadosCadastroFornecedor;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "fornecedor")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Boolean deletado;

    public Fornecedor(DadosCadastroFornecedor dados) {
    }

    public Fornecedor(Long id, String nome, String email, String telefone, Boolean deletado) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.deletado = false;
    }

    public void atualizarRegistro(DadosAtualizacaoFornecedor dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
    }
}
