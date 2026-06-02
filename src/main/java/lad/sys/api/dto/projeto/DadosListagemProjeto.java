package lad.sys.api.dto.projeto;

import lad.sys.api.model.Projeto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosListagemProjeto(
        Long id,
        String nome,
        String nomeCliente,
        Boolean ativo,
        BigDecimal orcamento,
        LocalDate dataInicio,
        Integer quantidadeUsuariosComAcesso
) {

    public DadosListagemProjeto(Projeto projeto) {
        this(
                projeto.getId(),
                projeto.getNome(),
                projeto.getCliente() != null ? projeto.getCliente().getNome() : null,
                projeto.getAtivo(),
                projeto.getOrcamento(),
                projeto.getDataInicio(),
                projeto.getUsuariosComAcesso() != null ? projeto.getUsuariosComAcesso().size() : 0
        );
    }
}