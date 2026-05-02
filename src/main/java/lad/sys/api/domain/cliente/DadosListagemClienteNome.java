package lad.sys.api.domain.cliente;

public record DadosListagemClienteNome(Long id, String nome) {

    public DadosListagemClienteNome(Cliente cliente) {
        this(cliente.getId(), cliente.getNome());
    }
}
