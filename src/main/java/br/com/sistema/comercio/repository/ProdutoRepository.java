package br.com.sistema.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.sistema.comercio.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
