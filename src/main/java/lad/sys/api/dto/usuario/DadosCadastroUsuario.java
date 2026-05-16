package lad.sys.api.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuario(

        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\c{8,30}")
        String senha,

        @Email
        @NotBlank
        String email,

        Tipo tipo) {
}
