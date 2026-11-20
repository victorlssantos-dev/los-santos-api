package projeto_1.Los.Santos.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_1.Los.Santos.infrastructure.entity.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
