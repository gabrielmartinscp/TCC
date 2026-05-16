package lad.sys.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "insumos")
public class Insumos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String unidade;
    private double precoAtual;
    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor idFornecedor;
}
