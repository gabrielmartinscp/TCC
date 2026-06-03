package lad.sys.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lad.sys.api.dto.projeto.DadosAtualizacaoProjeto;
import lad.sys.api.dto.projeto.DadosCadastroProjeto;
import lad.sys.api.dto.projeto.DadosListagemProjeto;
import lad.sys.api.dto.projeto.DadosProjeto;
import lad.sys.api.model.Projeto;
import lad.sys.api.repository.ClienteRepository;
import lad.sys.api.repository.ProjetoRepository;
import lad.sys.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
package lad.sys.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lad.sys.api.dto.projeto.DadosAtualizacaoProjeto;
import lad.sys.api.dto.projeto.DadosCadastroProjeto;
import lad.sys.api.dto.projeto.DadosListagemProjeto;
import lad.sys.api.dto.projeto.DadosProjeto;
import lad.sys.api.model.Projeto;
import lad.sys.api.repository.ClienteRepository;
import lad.sys.api.repository.ProjetoRepository;
import lad.sys.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private lad.sys.api.repository.OrcamentoRepository orcamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DadosListagemProjeto>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        var res = repository.findAllByAtivoTrue(pageable).map(DadosListagemProjeto::new);

        return ResponseEntity.ok(res);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> post(@RequestBody @Valid DadosCadastroProjeto dados, UriComponentsBuilder uriBuilder) {
        var cliente = clienteRepository.findById(dados.clienteId()).orElse(null);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        var projeto = new Projeto(dados, cliente);
        repository.save(projeto);

        if (dados.orcamento() != null) {
            var orc = new lad.sys.api.model.Orcamento();
            orc.setProjeto(projeto);
            orc.setUsuario(null);
            orc.setDataCriacao(java.time.LocalDate.now());
            orc.setCustoTotal(dados.orcamento());
            orc.setValorFinal(dados.orcamento());
            orcamentoRepository.save(orc);
        }

        var uri = uriBuilder.path("/projeto/{id}").buildAndExpand(projeto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosProjeto(projeto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> put(@RequestBody @Valid DadosAtualizacaoProjeto dados) {
        var projeto = repository.getReferenceByIdAndAtivoTrue(dados.id());
        if (projeto == null) {
            return ResponseEntity.notFound().build();
        }

        var cliente = dados.clienteId() != null ? clienteRepository.findById(dados.clienteId()).orElse(null) : null;
        if (dados.clienteId() != null && cliente == null) {
            return ResponseEntity.notFound().build();
        }

        projeto.atualizarRegistro(dados, cliente);

        return ResponseEntity.ok(new DadosProjeto(projeto));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosProjeto> getById(@PathVariable Long id) {
        var projeto = repository.getReferenceByIdAndAtivoTrue(id);
        return projeto != null ? ResponseEntity.ok(new DadosProjeto(projeto)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/usuarios")
    @Transactional
    public ResponseEntity<?> listarUsuariosAcesso(@PathVariable Long id) {
        var projeto = repository.getReferenceByIdAndAtivoTrue(id);
        return projeto != null ? ResponseEntity.ok(new DadosProjeto(projeto).usuariosComAcesso()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{projetoId}/usuarios/{usuarioId}")
    @Transactional
    public ResponseEntity<Object> adicionarAcesso(@PathVariable Long projetoId, @PathVariable Long usuarioId) {
        var projeto = repository.getReferenceByIdAndAtivoTrue(projetoId);
        if (projeto == null) {
            return ResponseEntity.notFound().build();
        }

        var usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        projeto.adicionarAcesso(usuario);

        return ResponseEntity.ok(new DadosProjeto(projeto));
    }

    @DeleteMapping("/{projetoId}/usuarios/{usuarioId}")
    @Transactional
    public ResponseEntity<Object> removerAcesso(@PathVariable Long projetoId, @PathVariable Long usuarioId) {
        var projeto = repository.getReferenceByIdAndAtivoTrue(projetoId);
        if (projeto == null) {
            return ResponseEntity.notFound().build();
        }

        var usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        projeto.removerAcesso(usuario);

        return ResponseEntity.ok(new DadosProjeto(projeto));
    }
}