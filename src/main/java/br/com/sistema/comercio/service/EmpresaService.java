package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Empresa;
import br.com.sistema.comercio.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    public Empresa createEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }
    
    public Empresa getEmpresaById(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }
    
    public Empresa updateEmpresa(Long id, Empresa empresa) {
        Empresa existingEmpresa = getEmpresaById(id);
        if (existingEmpresa != null) {
            existingEmpresa.setNome(empresa.getNome());
            existingEmpresa.setCnpj(empresa.getCnpj());
            existingEmpresa.setAtivo(empresa.getAtivo());
            return empresaRepository.save(existingEmpresa);
        }
        return null;
    }
    
    public void deleteEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }
}
