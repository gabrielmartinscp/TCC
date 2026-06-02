package lad.sys.api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lad.sys.api.dto.projeto.DadosAtualizacaoProjeto;
import lad.sys.api.dto.projeto.DadosCadastroProjeto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;
import java.util.Comparator;

@Table(name = "projeto")
@Entity(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projeto")
    private Long id;

    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private Boolean ativo;

    @Column(name = "tipo_obra")
    private String tipoObra;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @ManyToMany
    @JoinTable(
            name = "projetos_usuarios",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuariosComAcesso = new HashSet<>();

        @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<Orcamento> orcamentos = new HashSet<>();

    public Projeto(DadosCadastroProjeto dados, Cliente cliente) {
        this.id = null;
        this.nome = dados.nome();
        this.cliente = cliente;
        this.ativo = dados.ativo();
        this.dataInicio = dados.dataInicio();
        this.usuariosComAcesso = new HashSet<>();
    }

    public void atualizarRegistro(@Valid DadosAtualizacaoProjeto dados, Cliente cliente) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.cliente = cliente != null ? cliente : this.cliente;
        this.ativo = dados.ativo() != null ? dados.ativo() : this.ativo;
        this.dataInicio = dados.dataInicio() != null ? dados.dataInicio() : this.dataInicio;
    }

    public void adicionarAcesso(Usuario usuario) {
        this.usuariosComAcesso.add(usuario);
    }

    public void removerAcesso(Usuario usuario) {
        this.usuariosComAcesso.remove(usuario);
    }

    public BigDecimal getOrcamento() {
        return orcamentos.stream()
                .filter(o -> o.getValorFinal() != null || o.getCustoTotal() != null)
                .max(Comparator.comparing(Orcamento::getDataCriacao, Comparator.nullsLast(Comparator.naturalOrder())))
                .map(o -> o.getValorFinal() != null ? o.getValorFinal() : o.getCustoTotal())
                .orElse(null);
    }
}