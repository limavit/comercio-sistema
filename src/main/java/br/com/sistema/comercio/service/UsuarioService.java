package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Usuario;
import br.com.sistema.comercio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional
    public Usuario createUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email do usuário é obrigatório");
        }
        if (usuario.getEmpresa() == null) {
            throw new IllegalArgumentException("Empresa é obrigatória");
        }
        return usuarioRepository.save(usuario);
    }
    
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public Usuario updateUsuario(Long id, Usuario usuario) {
        Usuario existingUsuario = getUsuarioById(id);
        if (existingUsuario != null) {
            if (usuario.getNome() != null && !usuario.getNome().trim().isEmpty()) {
                existingUsuario.setNome(usuario.getNome());
            }
            if (usuario.getEmail() != null && !usuario.getEmail().trim().isEmpty()) {
                existingUsuario.setEmail(usuario.getEmail());
            }
            if (usuario.getPerfil() != null) {
                existingUsuario.setPerfil(usuario.getPerfil());
            }
            if (usuario.getAtivo() != null) {
                existingUsuario.setAtivo(usuario.getAtivo());
            }
            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }
    
    @Transactional
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    public List<Usuario> getUsuariosByEmpresa(Long empresaId) {
        return usuarioRepository.findByEmpresaId(empresaId);
    }
    
    public List<Usuario> getUsuariosAtivos() {
        return usuarioRepository.findByAtivoTrue();
    }
    
    public List<Usuario> getUsuariosByPerfil(Long perfilId) {
        return usuarioRepository.findByPerfilId(perfilId);
    }
}
