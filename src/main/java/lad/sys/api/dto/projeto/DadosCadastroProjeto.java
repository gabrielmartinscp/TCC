package lad.sys.api.dto.projeto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosCadastroProjeto(
        @NotBlank
        @Size(min = 3, max = 120)
        String nome,

        @NotNull
        Integer clienteId,

        @NotNull
        Boolean ativo,

        @PositiveOrZero
        BigDecimal orcamento,

        @NotNull
        @PastOrPresent
        LocalDate dataInicio,

        String tipoObra,

        String descricao,

        LocalDate dataFim
) {
}