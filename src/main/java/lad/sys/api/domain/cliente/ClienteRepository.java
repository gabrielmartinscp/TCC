package lad.sys.api.domain.cliente;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.groups.Default;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    Page<Cliente> findAllByDeletadoFalse(Pageable pageable);

    Cliente getReferenceByIdAndDeletadoFalse(Long id);

}
