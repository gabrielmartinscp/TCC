package lad.sys.api.dto.projeto;

import lad.sys.api.model.Projeto;

import java.time.LocalDate;

public record DadosListagemProjeto(
        Integer id,
        String nome,
        String nomeCliente,
        Boolean ativo,
        LocalDate dataInicio,
        LocalDate dataFim,
        Integer quantidadeUsuariosComAcesso
) {

    public DadosListagemProjeto(Projeto projeto) {
        this(
                projeto.getId(),
                projeto.getNome(),
                projeto.getCliente() != null ? projeto.getCliente().getNome() : null,
                projeto.getAtivo(),
                projeto.getDataInicio(),
                projeto.getDataFim(),
                projeto.getUsuariosComAcesso() != null ? projeto.getUsuariosComAcesso().size() : 0
        );
    }
}