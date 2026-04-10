package br.com.sistema.comercio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.sistema.comercio.model.Produto;
import br.com.sistema.comercio.repository.ProdutoRepository;

@Service
public class ProdutoService  {
    private final ProdutoRepository produtoRepository;
    
    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository)  {
        this.produtoRepository = produtoRepository;
    }
    
    public Produto createProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
}
