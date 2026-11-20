package projeto_1.Los.Santos.business;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projeto_1.Los.Santos.infrastructure.entity.Usuario;
import projeto_1.Los.Santos.infrastructure.exceptions.ConflictException;
import projeto_1.Los.Santos.infrastructure.exceptions.ResourceNotFoundException;
import projeto_1.Los.Santos.infrastructure.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvaUsuario(Usuario usuario) {
        // 1. Valida diretamente. Se o e-mail já existir, a exceção é lançada e o método pára aqui.
        validaEmailDuplicado(usuario.getEmail());
        validaCpfDuplicado(usuario.getCpf());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        // 2. Salva e devolve o usuário criado
        return usuarioRepository.save(usuario);
    }

    private void validaEmailDuplicado(String email) {
        // Se o repositório retornar true, disparamos a exceção de conflito imediatamente
        if (usuarioRepository.existsByEmail(email)) {
            throw new ConflictException("E-mail já cadastrado: " + email);
        }
    }

    private void validaCpfDuplicado(String cpf) {
        if (usuarioRepository.existsByCpf(cpf)) {
            throw new ConflictException("Esse Cpf já foi cadastrado: " + cpf);
        }
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("O Email não foi encontrado: " + email));

    }

    public void deletarPorEmail(String email) {
        Usuario usuario = buscarUsuarioPorEmail(email);
        usuarioRepository.delete(usuario);

    }
}