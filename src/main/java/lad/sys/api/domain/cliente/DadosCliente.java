package lad.sys.api.domain.cliente;

public record DadosCliente(Long id, String nome, String email, String telefone) {

    public DadosCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
    }
}
