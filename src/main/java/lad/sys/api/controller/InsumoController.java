package lad.sys.api.controller;

import jakarta.validation.Valid;
import lad.sys.api.dto.insumos.*;
import lad.sys.api.service.InsumoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/insumos")
public class InsumoController {

    private final InsumoService service;

    public InsumoController(InsumoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemInsumo>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemInsumo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DadosListagemInsumo> cadastrar(
            @RequestBody @Valid DadosCadastroInsumo dados,
            UriComponentsBuilder uriBuilder) {
        var resposta = service.cadastrar(dados);
        var uri = uriBuilder
                .path("/insumos/{id}")
                .buildAndExpand(resposta.id())
                .toUri();
        return ResponseEntity.created(uri).body(resposta);
    }

    @PutMapping
    public ResponseEntity<DadosListagemInsumo> atualizar(
            @RequestBody @Valid DadosAtualizacaoInsumo dados) {
        return ResponseEntity.ok(service.atualizar(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}