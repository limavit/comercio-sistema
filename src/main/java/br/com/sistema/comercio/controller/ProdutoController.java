package br.com.sistema.comercio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.sistema.comercio.model.Produto;
import br.com.sistema.comercio.service.ProdutoService;

@RestController
public class ProdutoController  {
    private final ProdutoService produtoService;
    
    @Autowired
    public ProdutoController(ProdutoService produtoService)  {
        this.produtoService = produtoService;
    }
    
    @PostMapping("/produtos")
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.createProduto(produto));
    }
}
