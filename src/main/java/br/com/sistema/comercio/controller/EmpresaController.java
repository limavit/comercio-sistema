package br.com.sistema.comercio.controller;

import br.com.sistema.comercio.model.Empresa;
import br.com.sistema.comercio.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    
    @Autowired
    private EmpresaService empresaService;
    
    @PostMapping
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        Empresa createdEmpresa = empresaService.createEmpresa(empresa);
        return ResponseEntity.ok(createdEmpresa);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable Long id) {
        Empresa empresa = empresaService.getEmpresaById(id);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        Empresa updatedEmpresa = empresaService.updateEmpresa(id, empresa);
        if (updatedEmpresa != null) {
            return ResponseEntity.ok(updatedEmpresa);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        empresaService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}
