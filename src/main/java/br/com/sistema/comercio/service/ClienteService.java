package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Cliente;
import br.com.sistema.comercio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Transactional
    public Cliente createCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }
        if (cliente.getEmpresa() == null) {
            throw new IllegalArgumentException("Empresa é obrigatória");
        }
        return clienteRepository.save(cliente);
    }
    
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente existingCliente = getClienteById(id);
        if (existingCliente != null) {
            if (cliente.getNome() != null && !cliente.getNome().trim().isEmpty()) {
                existingCliente.setNome(cliente.getNome());
            }
            if (cliente.getTelefone() != null) {
                existingCliente.setTelefone(cliente.getTelefone());
            }
            if (cliente.getEmail() != null) {
                existingCliente.setEmail(cliente.getEmail());
            }
            if (cliente.getEmpresa() != null) {
                existingCliente.setEmpresa(cliente.getEmpresa());
            }
            if (cliente.getAtivo() != null) {
                existingCliente.setAtivo(cliente.getAtivo());
            }
            return clienteRepository.save(existingCliente);
        }
        return null;
    }
    
    @Transactional
    public void deleteCliente(Long id) {
        Cliente cliente = getClienteById(id);
        if (cliente != null) {
            clienteRepository.delete(cliente);
        }
    }
    
    public List<Cliente> getClientesByEmpresa(Long empresaId) {
        return clienteRepository.findByEmpresaId(empresaId);
    }
    
    public List<Cliente> getClientesAtivos() {
        return clienteRepository.findByAtivoTrue();
    }
}
