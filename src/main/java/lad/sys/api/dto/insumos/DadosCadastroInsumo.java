package lad.sys.api.dto.insumos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DadosCadastroInsumo(

        @NotBlank
        String nome,

        @NotBlank
        String unidade,

        @Positive
        double precoAtual,

        @NotNull
        Long fornecedorId

) {
}