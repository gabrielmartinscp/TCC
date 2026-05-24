package lad.sys.api.dto.projeto;

import lad.sys.api.model.Projeto;

public record DadosProjeto(Long id_projeto, Long id_cliente, String nome, String tipo_obra, String descricao, String data_inicio, String data_fim) {

    public DadosProjeto(Projeto projeto) {
        this(projeto.getId_projeto(), projeto.getId_cliente(), projeto.getNome(), projeto.getTipo_obra(), projeto.getDescricao(), projeto.getData_inicio(), projeto.getData_fim());
    }
}