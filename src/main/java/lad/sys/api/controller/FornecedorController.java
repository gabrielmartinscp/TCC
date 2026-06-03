package lad.sys.api.controller;


import jakarta.validation.Valid;
import lad.sys.api.dto.cliente.*;
import lad.sys.api.dto.fornecedor.DadosAtualizacaoFornecedor;
import lad.sys.api.dto.fornecedor.DadosCadastroFornecedor;
import lad.sys.api.model.Fornecedor;
import lad.sys.api.service.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Fornecedor> cadastrar(
            @RequestBody @Valid DadosCadastroFornecedor dados) {
        return ResponseEntity.ok(service.cadastrar(dados));
    }

    @PutMapping
    public ResponseEntity<Fornecedor> atualizar(
            @RequestBody @Valid DadosAtualizacaoFornecedor dados) {
        return ResponseEntity.ok(service.atualizar(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
