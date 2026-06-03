package lad.sys.api.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lad.sys.api.dto.insumos.DadosAtualizacaoInsumo;
import lad.sys.api.dto.insumos.DadosCadastroInsumo;
import lad.sys.api.dto.insumos.DadosListagemInsumo;
import lad.sys.api.model.Fornecedor;
import lad.sys.api.model.Insumo;
import lad.sys.api.repository.FornecedorRepository;
import lad.sys.api.repository.InsumoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsumoService {

    private final InsumoRepository repository;
    private final FornecedorRepository fornecedorRepository;

    public InsumoService(
            InsumoRepository repository,
            FornecedorRepository fornecedorRepository) {

        this.repository = repository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<DadosListagemInsumo> listarTodos() {
        return repository.findAll()
                .stream()
                .map(DadosListagemInsumo::new)
                .toList();
    }

    public DadosListagemInsumo buscarPorId(Long id) {
        var insumo = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Insumo não encontrado"));
        return new DadosListagemInsumo(insumo);
    }

    @Transactional
    public DadosListagemInsumo cadastrar(DadosCadastroInsumo dados) {
        var fornecedor = fornecedorRepository.findById(dados.fornecedorId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Fornecedor não encontrado"));
        var insumo = new Insumo(dados, fornecedor);
        repository.save(insumo);
        return new DadosListagemInsumo(insumo);
    }

    @Transactional
    public DadosListagemInsumo atualizar(DadosAtualizacaoInsumo dados) {
        var insumo = repository.findById(dados.id())
                .orElseThrow(() ->
                        new EntityNotFoundException("Insumo não encontrado"));
        Fornecedor fornecedor = null;
        if (dados.fornecedorId() != null) {
            fornecedor = fornecedorRepository.findById(dados.fornecedorId())
                    .orElseThrow(() ->
                            new EntityNotFoundException("Fornecedor não encontrado"));
        }
        insumo.atualizarRegistro(dados, fornecedor);
        return new DadosListagemInsumo(insumo);
    }

    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
