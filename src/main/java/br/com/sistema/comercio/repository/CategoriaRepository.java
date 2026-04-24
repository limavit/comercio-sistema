package br.com.sistema.comercio.repository;

import br.com.sistema.comercio.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    List<Categoria> findByEmpresaId(Long empresaId);
    
    List<Categoria> findByAtivoTrue();
}
