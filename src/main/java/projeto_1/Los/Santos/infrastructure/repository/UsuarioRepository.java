package projeto_1.Los.Santos.infrastructure.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import projeto_1.Los.Santos.infrastructure.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    boolean existsByCpf(String cpf);

    Optional<Usuario> findByCpf(String cpf);
}

