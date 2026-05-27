package lad.sys.api.dto.projeto;

import lad.sys.api.dto.usuario.Tipo;
import lad.sys.api.model.Usuario;

public record DadosUsuarioAcesso(
        Long id,
        String nome,
        String email,
        Tipo tipo
) {

    public DadosUsuarioAcesso(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTipo());
    }
}