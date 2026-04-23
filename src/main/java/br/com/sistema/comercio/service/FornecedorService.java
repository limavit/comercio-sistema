package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Fornecedor;
import br.com.sistema.comercio.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {
    
    @Autowired
    private FornecedorRepository fornecedorRepository;
    
    public Fornecedor createFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }
    
    public Fornecedor getFornecedorById(Long id) {
        return fornecedorRepository.findById(id).orElse(null);
    }
    
    public Fornecedor updateFornecedor(Long id, Fornecedor fornecedor) {
        Fornecedor existingFornecedor = getFornecedorById(id);
        if (existingFornecedor != null) {
            existingFornecedor.setNome(fornecedor.getNome());
            existingFornecedor.setTelefone(fornecedor.getTelefone());
            existingFornecedor.setEmpresa(fornecedor.getEmpresa());
            existingFornecedor.setAtivo(fornecedor.getAtivo());
            return fornecedorRepository.save(existingFornecedor);
        }
        return null;
    }
    
    public void deleteFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
