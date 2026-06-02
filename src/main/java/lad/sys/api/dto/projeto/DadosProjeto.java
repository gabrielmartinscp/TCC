package lad.sys.api.dto.projeto;

import lad.sys.api.model.Projeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DadosProjeto(
        Long id,
        String nome,
        Long clienteId,
        String nomeCliente,
        Boolean ativo,
        BigDecimal orcamento,
        LocalDate dataInicio,
        List<DadosUsuarioAcesso> usuariosComAcesso
) {

    public DadosProjeto(Projeto projeto) {
        this(
                projeto.getId(),
                projeto.getNome(),
                projeto.getCliente() != null ? projeto.getCliente().getId() : null,
                projeto.getCliente() != null ? projeto.getCliente().getNome() : null,
                projeto.getAtivo(),
                projeto.getOrcamento(),
                projeto.getDataInicio(),
                projeto.getUsuariosComAcesso().stream().map(DadosUsuarioAcesso::new).toList()
        );
    }
}