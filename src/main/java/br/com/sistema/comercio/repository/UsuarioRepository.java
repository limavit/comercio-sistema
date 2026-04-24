package br.com.sistema.comercio.repository;

import br.com.sistema.comercio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    List<Usuario> findByEmpresaId(Long empresaId);
    
    List<Usuario> findByAtivoTrue();
    
    List<Usuario> findByPerfilId(Long perfilId);
}
