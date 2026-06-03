package lad.sys.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lad.sys.api.dto.orcamento.DadosCadastroOrcamento;
import lad.sys.api.dto.orcamento.DadosOrcamento;
import lad.sys.api.model.Orcamento;
import lad.sys.api.model.Projeto;
import lad.sys.api.model.Usuario;
import lad.sys.api.repository.OrcamentoRepository;
import lad.sys.api.repository.ProjetoRepository;
import lad.sys.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {

    @Autowired
    private OrcamentoRepository repository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> criar(@RequestBody @Valid DadosCadastroOrcamento dados) {
        var projeto = projetoRepository.getReferenceByIdAndAtivoTrue(dados.projetoId());
        if (projeto == null) return ResponseEntity.notFound().build();

        var usuario = usuarioRepository.findById(dados.usuarioId()).orElse(null);
        if (usuario == null) return ResponseEntity.notFound().build();

        var orc = new Orcamento();
        orc.setProjeto(projeto);
        orc.setUsuario(usuario);
        orc.setDataCriacao(dados.dataCriacao() != null ? dados.dataCriacao() : LocalDate.now());
        orc.setCustoTotal(dados.custoTotal());
        orc.setValorFinal(dados.valorFinal());
        orc.setMargemLucro(dados.margemLucro());
        orc.setImpostos(dados.impostos());

        repository.save(orc);

        return ResponseEntity.ok(new DadosOrcamento(orc));
    }

    @GetMapping("/projeto/{projetoId}")
    @Transactional
    public ResponseEntity<List<DadosOrcamento>> listarPorProjeto(@PathVariable Integer projetoId) {
        var lista = repository.findByProjetoId(projetoId).stream().map(DadosOrcamento::new).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosOrcamento> getById(@PathVariable Integer id) {
        var orc = repository.findById(id).orElse(null);
        return orc != null ? ResponseEntity.ok(new DadosOrcamento(orc)) : ResponseEntity.notFound().build();
    }
}
