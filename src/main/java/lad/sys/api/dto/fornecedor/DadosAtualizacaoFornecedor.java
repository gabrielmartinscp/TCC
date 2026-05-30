package lad.sys.api.dto.fornecedor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoFornecedor(

        @NotNull
        Long id,

        String nome,

        @Email
        String email,

        String telefone

) {
}