package br.com.sistema.comercio.controller;

import br.com.sistema.comercio.model.Usuario;
import br.com.sistema.comercio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario createdUsuario = usuarioService.createUsuario(usuario);
            return ResponseEntity.ok(createdUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Usuario>> getUsuariosByEmpresa(@PathVariable Long empresaId) {
        List<Usuario> usuarios = usuarioService.getUsuariosByEmpresa(empresaId);
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/ativos")
    public ResponseEntity<List<Usuario>> getUsuariosAtivos() {
        List<Usuario> usuarios = usuarioService.getUsuariosAtivos();
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<Usuario>> getUsuariosByPerfil(@PathVariable Long perfilId) {
        List<Usuario> usuarios = usuarioService.getUsuariosByPerfil(perfilId);
        return ResponseEntity.ok(usuarios);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
            if (updatedUsuario != null) {
                return ResponseEntity.ok(updatedUsuario);
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
