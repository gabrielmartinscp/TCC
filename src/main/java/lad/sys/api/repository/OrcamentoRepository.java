package lad.sys.api.repository;

import lad.sys.api.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {
    List<Orcamento> findByProjetoId(Integer projetoId);
}
