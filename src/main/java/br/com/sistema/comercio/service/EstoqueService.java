package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Estoque;
import br.com.sistema.comercio.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {
    
    @Autowired
    private EstoqueRepository estoqueRepository;
    
    public Estoque createEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }
    
    public Estoque getEstoqueById(Long id) {
        return estoqueRepository.findById(id).orElse(null);
    }
    
    public Estoque updateEstoque(Long id, Estoque estoque) {
        Estoque existingEstoque = getEstoqueById(id);
        if (existingEstoque != null) {
            existingEstoque.setProduto(estoque.getProduto());
            existingEstoque.setEmpresa(estoque.getEmpresa());
            existingEstoque.setQuantidade(estoque.getQuantidade());
            return estoqueRepository.save(existingEstoque);
        }
        return null;
    }
    
    public void deleteEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }
}
