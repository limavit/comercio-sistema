package br.com.sistema.comercio.controller;

import br.com.sistema.comercio.model.Categoria;
import br.com.sistema.comercio.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        try {
            Categoria createdCategoria = categoriaService.createCategoria(categoria);
            return ResponseEntity.ok(createdCategoria);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Categoria>> getCategoriasByEmpresa(@PathVariable Long empresaId) {
        List<Categoria> categorias = categoriaService.getCategoriasByEmpresa(empresaId);
        return ResponseEntity.ok(categorias);
    }
    
    @GetMapping("/ativos")
    public ResponseEntity<List<Categoria>> getCategoriasAtivas() {
        List<Categoria> categorias = categoriaService.getCategoriasAtivas();
        return ResponseEntity.ok(categorias);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            Categoria updatedCategoria = categoriaService.updateCategoria(id, categoria);
            if (updatedCategoria != null) {
                return ResponseEntity.ok(updatedCategoria);
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
