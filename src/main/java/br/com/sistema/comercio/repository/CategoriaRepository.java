package br.com.sistema.comercio.repository;

import br.com.sistema.comercio.model.Categoria;
import br.com.sistema.comercio.model.Empresa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	
	List<Categoria> getCategoriasByEmpresa(Empresa emp);

	boolean existsByNomeIgnoreCaseAndEmpresa(String nome, Empresa empresa);
}

