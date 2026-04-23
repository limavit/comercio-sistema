package br.com.sistema.comercio.repository;

import br.com.sistema.comercio.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    List<Cliente> findByEmpresaId(Long empresaId);
    
    List<Cliente> findByAtivoTrue();
}
