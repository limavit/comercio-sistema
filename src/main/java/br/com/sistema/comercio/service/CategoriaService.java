package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Categoria;
import br.com.sistema.comercio.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Transactional
    public Categoria createCategoria(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da categoria é obrigatório");
        }
        if (categoria.getEmpresa() == null) {
            throw new IllegalArgumentException("Empresa é obrigatória");
        }
        return categoriaRepository.save(categoria);
    }
    
    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public Categoria updateCategoria(Long id, Categoria categoria) {
        Categoria existingCategoria = getCategoriaById(id);
        if (existingCategoria != null) {
            if (categoria.getNome() != null && !categoria.getNome().trim().isEmpty()) {
                existingCategoria.setNome(categoria.getNome());
            }
            if (categoria.getAtivo() != null) {
                existingCategoria.setAtivo(categoria.getAtivo());
            }
            return categoriaRepository.save(existingCategoria);
        }
        return null;
    }
    
    @Transactional
    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
    
    public List<Categoria> getCategoriasByEmpresa(Long empresaId) {
        return categoriaRepository.findByEmpresaId(empresaId);
    }
    
    public List<Categoria> getCategoriasAtivas() {
        return categoriaRepository.findByAtivoTrue();
    }
}
