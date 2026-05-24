package lad.sys.api.dto.orcamento;


import lad.sys.api.model.Orcamento;

public record DadosListagemOrcamento(Long id_usuario, String data_criacao, Float custo_total, Float valor_final, Float margem_lucro, Float impostos) {

    public DadosListagemOrcamento(Orcamento orcamento) {
        this(orcamento.getId(), orcamento.getDataCriacao(), orcamento.getCustoTotal(), orcamento.getValorFinal(),  orcamento.getMargemLucro(), orcamento.getImpostos());
    }
}
