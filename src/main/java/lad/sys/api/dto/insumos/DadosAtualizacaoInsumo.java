package lad.sys.api.dto.insumos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DadosAtualizacaoInsumo(

        @NotNull
        Long id,
        String nome,
        String unidade,
        @Positive
        Double precoAtual,
        Long fornecedorId

) {
}