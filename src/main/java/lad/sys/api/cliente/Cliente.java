package lad.sys.api.cliente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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
    private String email;
    private String telefone;
    private Boolean deletado;

    public Cliente(DadosCadastroCliente dados) {
        this.id = null;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.deletado = false;
    }

    public void atualizarRegistro(@Valid DadosAtualizacaoCliente dados) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.email = dados.email() != null ? dados.email() : this.email;
        this.telefone = dados.telefone() != null ? dados.telefone() : this.telefone;
    }

    public void deletarCliente() {
        this.deletado = true;
    }
}
