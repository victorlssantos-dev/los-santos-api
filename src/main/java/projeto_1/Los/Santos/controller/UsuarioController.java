package projeto_1.Los.Santos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import projeto_1.Los.Santos.business.UsuarioService;
import projeto_1.Los.Santos.controller.dtos.UsuarioDTO;
import projeto_1.Los.Santos.infrastructure.entity.Usuario;
import projeto_1.Los.Santos.security.JwtUtil;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuario));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
       Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha()));
       return jwtUtil.generateToken(authentication.getName());

    }

    @GetMapping()
    public ResponseEntity<Usuario> buscarUsuarioPorEmail (@RequestParam("email") String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email:.+}")
    public ResponseEntity<Void> deletarPorEmail (@PathVariable String email){
        usuarioService.deletarPorEmail(email);
        return ResponseEntity.ok().build();
    }
}
