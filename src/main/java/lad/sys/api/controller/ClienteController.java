package lad.sys.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lad.sys.api.cliente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public Page<DadosListagemCliente> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        return repository.findAllByDeletadoFalse(pageable).map(DadosListagemCliente::new);
    }

    @GetMapping
    @RequestMapping("/nomes")
    public Page<DadosListagemClienteNome> listarnomes(Pageable pageable) {
        return repository.findAllByDeletadoFalse(pageable).map(DadosListagemClienteNome::new);
    }

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid DadosCadastroCliente dados) {
        repository.save(new Cliente(dados));
    }

    @PutMapping
    @Transactional
    public void put(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarRegistro(dados);
    }

    @GetMapping("/{id}")
    public DadosCliente getById(@PathVariable Long id){
        Cliente cliente = repository.getReferenceByIdAndDeletadoFalse(id);
        return new DadosCliente(cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
    }

    @DeleteMapping
    @Transactional
    public void deleteById(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        Cliente cliente = repository.getReferenceById(dados.id());
        cliente.deletarCliente();
    }
}
