package lad.sys.api.dto.projeto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizacaoProjeto(
        @NotNull
        Long id,

        @Size(min = 3, max = 120)
        String nome,

        Long clienteId,

        Boolean ativo,

        @PositiveOrZero
        BigDecimal orcamento,

        @PastOrPresent
        LocalDate dataInicio
) {
}