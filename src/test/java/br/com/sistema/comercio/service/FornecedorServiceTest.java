package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Fornecedor;
import br.com.sistema.comercio.model.Empresa;
import br.com.sistema.comercio.repository.FornecedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FornecedorServiceTest {

    @Mock
    private FornecedorRepository fornecedorRepository;

    @InjectMocks
    private FornecedorService fornecedorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFornecedor() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor Teste");
        fornecedor.setTelefone("(11) 1234-5678");
        fornecedor.setAtivo(true);
        
        when(fornecedorRepository.save(fornecedor)).thenReturn(fornecedor);
        
        Fornecedor result = fornecedorService.createFornecedor(fornecedor);
        
        assertNotNull(result);
        assertEquals("Fornecedor Teste", result.getNome());
        assertEquals("(11) 1234-5678", result.getTelefone());
        assertTrue(result.getAtivo());
        verify(fornecedorRepository, times(1)).save(fornecedor);
    }

    @Test
    void testGetFornecedorById() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1L);
        fornecedor.setNome("Fornecedor Teste");
        fornecedor.setTelefone("(11) 1234-5678");
        
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedor));
        
        Fornecedor result = fornecedorService.getFornecedorById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Fornecedor Teste", result.getNome());
        assertEquals("(11) 1234-5678", result.getTelefone());
        verify(fornecedorRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateFornecedor() {
        Fornecedor existingFornecedor = new Fornecedor();
        existingFornecedor.setId(1L);
        existingFornecedor.setNome("Fornecedor Antigo");
        existingFornecedor.setTelefone("(11) 1111-1111");
        existingFornecedor.setAtivo(true);
        
        Fornecedor updatedFornecedor = new Fornecedor();
        updatedFornecedor.setNome("Fornecedor Atualizado");
        updatedFornecedor.setTelefone("(11) 2222-2222");
        updatedFornecedor.setAtivo(false);
        
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(existingFornecedor));
        when(fornecedorRepository.save(existingFornecedor)).thenReturn(existingFornecedor);
        
        Fornecedor result = fornecedorService.updateFornecedor(1L, updatedFornecedor);
        
        assertNotNull(result);
        assertEquals("Fornecedor Atualizado", result.getNome());
        assertEquals("(11) 2222-2222", result.getTelefone());
        assertFalse(result.getAtivo());
        verify(fornecedorRepository, times(1)).save(existingFornecedor);
    }

    @Test
    void testDeleteFornecedor() {
        doNothing().when(fornecedorRepository).deleteById(1L);
        
        fornecedorService.deleteFornecedor(1L);
        
        verify(fornecedorRepository, times(1)).deleteById(1L);
    }
}
