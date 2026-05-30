package lad.sys.api.model;

import jakarta.persistence.*;
import lad.sys.api.dto.insumos.DadosAtualizacaoInsumo;
import lad.sys.api.dto.insumos.DadosCadastroInsumo;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "insumo")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String unidade;
    private double precoAtual;
    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor idFornecedor;

    public Insumo(DadosCadastroInsumo dados, Fornecedor fornecedor) {
        this.nome = dados.nome();
        this.unidade = dados.unidade();
        this.precoAtual = dados.precoAtual();
        this.idFornecedor = fornecedor;
    }

    public void atualizarRegistro(
            DadosAtualizacaoInsumo dados,
            Fornecedor fornecedor
    ) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.unidade() != null) {
            this.unidade = dados.unidade();
        }
        if (dados.precoAtual() != null) {
            this.precoAtual = dados.precoAtual();
        }
        if (fornecedor != null) {
            this.idFornecedor = fornecedor;
        }
    }
}
