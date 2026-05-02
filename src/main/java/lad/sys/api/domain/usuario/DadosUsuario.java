package lad.sys.api.domain.usuario;

public record DadosUsuario(Long id, String nome, String senha, String email, Tipo tipo) {

    public DadosUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getSenha(), usuario.getEmail(), usuario.getTipo());
    }
}
