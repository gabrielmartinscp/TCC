package lad.sys.api.dto.projeto;

import lad.sys.api.model.Projeto;

import java.time.LocalDate;
import java.util.List;

public record DadosProjeto(
        Integer id,
        String nome,
        Integer clienteId,
        String nomeCliente,
        Boolean ativo,
        String tipoObra,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        List<DadosUsuarioAcesso> usuariosComAcesso
) {

    public DadosProjeto(Projeto projeto) {
        this(
                projeto.getId(),
                projeto.getNome(),
                projeto.getCliente() != null ? (projeto.getCliente().getId() != null ? projeto.getCliente().getId().intValue() : null) : null,
                projeto.getCliente() != null ? projeto.getCliente().getNome() : null,
                projeto.getAtivo(),
                projeto.getTipoObra(),
                projeto.getDescricao(),
                projeto.getDataInicio(),
                projeto.getDataFim(),
                projeto.getUsuariosComAcesso().stream().map(DadosUsuarioAcesso::new).toList()
        );
    }
}