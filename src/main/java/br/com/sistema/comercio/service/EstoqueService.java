package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Estoque;
import br.com.sistema.comercio.repository.EstoqueRepository;
import jakarta.transaction.Transactional;

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
    
    @Transactional
    public void atualizarEstoque(Long produtoId, Integer quantidade) {
        Estoque estoque = getEstoqueByProduto(produtoId);
        if (estoque != null) {
            Integer novaQuantidade = estoque.getQuantidade() - quantidade;
            if (novaQuantidade < 0) {
                throw new RuntimeException("Estoque insuficiente para o produto ID: " + produtoId);
            }
            estoque.setQuantidade(novaQuantidade);
            estoqueRepository.save(estoque);
        }
    }
    
    @Transactional
    public void adicionarEstoque(Long produtoId, Integer quantidade) {
        Estoque estoque = getEstoqueByProduto(produtoId);
        if (estoque != null) {
            Integer novaQuantidade = estoque.getQuantidade() + quantidade;
            estoque.setQuantidade(novaQuantidade);
            estoqueRepository.save(estoque);
        }
    }
    
    @Transactional(readOnly = true)
    public Estoque getEstoqueByProduto(Long produtoId) {
        return estoqueRepository.findByProdutoId(produtoId);
    }
}
