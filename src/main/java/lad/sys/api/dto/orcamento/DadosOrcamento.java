package lad.sys.api.dto.orcamento;

import lad.sys.api.model.Orcamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosOrcamento(
        Integer id,
        Integer projetoId,
        Long usuarioId,
        LocalDate dataCriacao,
        BigDecimal custoTotal,
        BigDecimal valorFinal,
        BigDecimal margemLucro,
        BigDecimal impostos
) {

    public DadosOrcamento(Orcamento o) {
        this(
                o.getId(),
                o.getProjeto() != null ? o.getProjeto().getId() : null,
                o.getUsuario() != null ? o.getUsuario().getId() : null,
                o.getDataCriacao(),
                o.getCustoTotal(),
                o.getValorFinal(),
                o.getMargemLucro(),
                o.getImpostos()
        );
    }
}
