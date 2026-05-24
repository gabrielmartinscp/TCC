package lad.sys.api.repository;

import lad.sys.api.model.Orcamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    Orcamento getReferenceByIdAndDeletadoFalse(Long id);

    Page<Orcamento> findAllByDeletadoFalse(Pageable pageable);

}
