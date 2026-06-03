package lad.sys.api.dto.fornecedor;

import lad.sys.api.model.Fornecedor;

public record DadosListagemFornecedor(
        Long id,
        String nome,
        String email,
        String telefone
) {

    public static DadosListagemFornecedor entityToResponse(Fornecedor entity) {
        return new DadosListagemFornecedor(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone()
        );
    }
}