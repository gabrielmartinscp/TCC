package lad.sys.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lad.sys.api.dto.projeto.DadosAtualizacaoProjeto;
import lad.sys.api.dto.projeto.DadosCadastroProjeto;
import lad.sys.api.dto.projeto.DadosListagemProjeto;
import lad.sys.api.dto.projeto.DadosProjeto;
import lad.sys.api.model.Projeto;
import lad.sys.api.repository.ProjetoRepository;
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
    ProjetoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemProjeto>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        var res = repository.findAllByDeletadoFalse(pageable).map(DadosListagemProjeto::new);

        return ResponseEntity.ok(res);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        Projeto projeto = repository.getReferenceByIdAndDeletadoFalse(id);

        return projeto != null ? ResponseEntity.ok(projeto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> post(@RequestBody @Valid DadosCadastroProjeto dados, UriComponentsBuilder uriBuilder){
        var projeto = new Projeto(dados);
        repository.save(projeto);

        var uri = uriBuilder.path("/projeto/{id}").buildAndExpand(projeto.getId_projeto()).toUri();

        return ResponseEntity.created(uri).body(new DadosProjeto(projeto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> put(@RequestBody @Valid DadosAtualizacaoProjeto dados) {
        var projeto = repository.getReferenceById(dados.id_projeto());
        projeto.atualizarRegistro(dados);

        var res = new DadosProjeto(projeto);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> deleteById(@RequestBody @Valid DadosAtualizacaoProjeto dados) {
        Projeto projeto = repository.getReferenceById(dados.id_projeto());
        projeto.deletarProjeto();

        return ResponseEntity.noContent().build();
    }
}
