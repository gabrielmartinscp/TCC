package lad.sys.api.cliente;

public record DadosListagemClienteNome(Long id, String nome) {

    public DadosListagemClienteNome(Cliente cliente) {
        this(cliente.getId(), cliente.getNome());
    }
}
