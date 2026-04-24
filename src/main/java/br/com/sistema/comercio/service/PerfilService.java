package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Perfil;
import br.com.sistema.comercio.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilService {
    
    @Autowired
    private PerfilRepository perfilRepository;
    
    @Transactional
    public Perfil createPerfil(Perfil perfil) {
        if (perfil.getNome() == null || perfil.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do perfil é obrigatório");
        }
        return perfilRepository.save(perfil);
    }
    
    public Perfil getPerfilById(Long id) {
        return perfilRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public Perfil updatePerfil(Long id, Perfil perfil) {
        Perfil existingPerfil = getPerfilById(id);
        if (existingPerfil != null) {
            if (perfil.getNome() != null && !perfil.getNome().trim().isEmpty()) {
                existingPerfil.setNome(perfil.getNome());
            }
            return perfilRepository.save(existingPerfil);
        }
        return null;
    }
    
    @Transactional
    public void deletePerfil(Long id) {
        perfilRepository.deleteById(id);
    }
}
