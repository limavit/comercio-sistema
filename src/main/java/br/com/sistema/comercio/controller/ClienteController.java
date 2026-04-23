package br.com.sistema.comercio.controller;

import br.com.sistema.comercio.model.Cliente;
import br.com.sistema.comercio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente createdCliente = clienteService.createCliente(cliente);
            return ResponseEntity.ok(createdCliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Cliente>> getClientesByEmpresa(@PathVariable Long empresaId) {
        List<Cliente> clientes = clienteService.getClientesByEmpresa(empresaId);
        return ResponseEntity.ok(clientes);
    }
    
    @GetMapping("/ativos")
    public ResponseEntity<List<Cliente>> getClientesAtivos() {
        List<Cliente> clientes = clienteService.getClientesAtivos();
        return ResponseEntity.ok(clientes);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente updatedCliente = clienteService.updateCliente(id, cliente);
            if (updatedCliente != null) {
                return ResponseEntity.ok(updatedCliente);
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
