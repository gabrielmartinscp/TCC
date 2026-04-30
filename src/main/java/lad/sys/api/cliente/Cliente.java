package lad.sys.api.cliente;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "clientes")
@Entity(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    //
    //
    //

    public Cliente(DadosCadastroCliente dados) {
        this.id = null;
        this.nome = dados.nome();
    }
}
