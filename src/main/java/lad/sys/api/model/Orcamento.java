package lad.sys.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "orcamento")
@Entity(name = "orcamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orcamento")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_projeto", nullable = false)
    private Projeto projeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "custo_total", precision = 12, scale = 2)
    private BigDecimal custoTotal;

    @Column(name = "valor_final", precision = 12, scale = 2)
    private BigDecimal valorFinal;

    @Column(name = "margem_lucro", precision = 5, scale = 2)
    private BigDecimal margemLucro;

    @Column(name = "impostos", precision = 5, scale = 2)
    private BigDecimal impostos;

    // Lombok fornece construtores
}
