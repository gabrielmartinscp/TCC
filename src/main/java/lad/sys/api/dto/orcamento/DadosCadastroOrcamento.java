package lad.sys.api.dto.orcamento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosCadastroOrcamento(
        @NotNull
        Integer projetoId,

        @NotNull
        Long usuarioId,

        @PastOrPresent
        LocalDate dataCriacao,

        BigDecimal custoTotal,

        BigDecimal valorFinal,

        BigDecimal margemLucro,

        BigDecimal impostos
) {
}
