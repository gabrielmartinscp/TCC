package lad.sys.api.dto.insumos;

import lad.sys.api.model.Insumo;

public record DadosListagemInsumo(
        Long id,
        String nome,
        String unidade,
        double precoAtual,
        String fornecedor
) {

    public DadosListagemInsumo(Insumo insumo) {
        this(
                insumo.getId(),
                insumo.getNome(),
                insumo.getUnidade(),
                insumo.getPrecoAtual(),
                insumo.getIdFornecedor().getNome()
        );
    }
}