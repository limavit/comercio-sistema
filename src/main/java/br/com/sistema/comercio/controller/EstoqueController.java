package br.com.sistema.comercio.controller;

import br.com.sistema.comercio.model.Estoque;
import br.com.sistema.comercio.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {
    
    @Autowired
    private EstoqueService estoqueService;
    
    @PostMapping
    public ResponseEntity<Estoque> createEstoque(@RequestBody Estoque estoque) {
        Estoque createdEstoque = estoqueService.createEstoque(estoque);
        return ResponseEntity.ok(createdEstoque);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Estoque> getEstoque(@PathVariable Long id) {
        Estoque estoque = estoqueService.getEstoqueById(id);
        if (estoque != null) {
            return ResponseEntity.ok(estoque);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Estoque> updateEstoque(@PathVariable Long id, @RequestBody Estoque estoque) {
        Estoque updatedEstoque = estoqueService.updateEstoque(id, estoque);
        if (updatedEstoque != null) {
            return ResponseEntity.ok(updatedEstoque);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstoque(@PathVariable Long id) {
        estoqueService.deleteEstoque(id);
        return ResponseEntity.noContent().build();
    }
}
