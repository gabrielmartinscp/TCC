package lad.sys.api.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lad.sys.api.dto.fornecedor.DadosAtualizacaoFornecedor;
import lad.sys.api.dto.fornecedor.DadosCadastroFornecedor;
import lad.sys.api.model.Fornecedor;
import lad.sys.api.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public List<Fornecedor> listarTodos() {
        return repository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Fornecedor não encontrado"));
    }

    @Transactional
    public Fornecedor cadastrar(DadosCadastroFornecedor dados) {
        Fornecedor fornecedor = new Fornecedor(dados);
        return repository.save(fornecedor);
    }

    @Transactional
    public Fornecedor atualizar(DadosAtualizacaoFornecedor dados) {
        Fornecedor fornecedor = repository.findById(dados.id())
                .orElseThrow(() ->
                        new EntityNotFoundException("Fornecedor não encontrado"));
        fornecedor.atualizarRegistro(dados);
        return fornecedor;
    }

    @Transactional
    public void excluir(Long id) {
        Fornecedor fornecedor = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Fornecedor não encontrado"));

        repository.delete(fornecedor);
    }
}
