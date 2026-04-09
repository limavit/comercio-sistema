package br.com.sistema.comercio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.sistema.comercio.model.Produto;
import br.com.sistema.comercio.service.ProdutoService;

@RestController
public class ProdutoController {
    private final ProdutoService produtoService;
    
    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    
    // controller methods...
}
