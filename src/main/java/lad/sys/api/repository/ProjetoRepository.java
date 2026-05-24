package lad.sys.api.repository;

import lad.sys.api.model.Projeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    Page<Projeto> findAllByDeletadoFalse(Pageable pageable);

    Projeto getReferenceByIdAndDeletadoFalse(Long id);
}
