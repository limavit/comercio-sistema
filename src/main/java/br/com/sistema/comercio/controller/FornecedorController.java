package br.com.sistema.comercio.controller;

import br.com.sistema.comercio.model.Fornecedor;
import br.com.sistema.comercio.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
    
    @Autowired
    private FornecedorService fornecedorService;
    
    @PostMapping
    public ResponseEntity<Fornecedor> createFornecedor(@RequestBody Fornecedor fornecedor) {
        Fornecedor createdFornecedor = fornecedorService.createFornecedor(fornecedor);
        return ResponseEntity.ok(createdFornecedor);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> getFornecedor(@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorService.getFornecedorById(id);
        if (fornecedor != null) {
            return ResponseEntity.ok(fornecedor);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        Fornecedor updatedFornecedor = fornecedorService.updateFornecedor(id, fornecedor);
        if (updatedFornecedor != null) {
            return ResponseEntity.ok(updatedFornecedor);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
        fornecedorService.deleteFornecedor(id);
        return ResponseEntity.noContent().build();
    }
}
