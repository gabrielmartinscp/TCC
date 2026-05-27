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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "projetos")
@Entity(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private Boolean ativo;

    @Column(precision = 15, scale = 2)
    private BigDecimal orcamento;

    private LocalDate dataInicio;

    @ManyToMany
    @JoinTable(
            name = "projetos_usuarios",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuariosComAcesso = new HashSet<>();

    public Projeto(DadosCadastroProjeto dados, Cliente cliente) {
        this.id = null;
        this.nome = dados.nome();
        this.cliente = cliente;
        this.ativo = dados.ativo();
        this.orcamento = dados.orcamento();
        this.dataInicio = dados.dataInicio();
        this.usuariosComAcesso = new HashSet<>();
    }

    public void atualizarRegistro(@Valid DadosAtualizacaoProjeto dados, Cliente cliente) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.cliente = cliente != null ? cliente : this.cliente;
        this.ativo = dados.ativo() != null ? dados.ativo() : this.ativo;
        this.orcamento = dados.orcamento() != null ? dados.orcamento() : this.orcamento;
        this.dataInicio = dados.dataInicio() != null ? dados.dataInicio() : this.dataInicio;
    }

    public void adicionarAcesso(Usuario usuario) {
        this.usuariosComAcesso.add(usuario);
    }

    public void removerAcesso(Usuario usuario) {
        this.usuariosComAcesso.remove(usuario);
    }
}