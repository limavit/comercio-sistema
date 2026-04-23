package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Estoque;
import br.com.sistema.comercio.model.Empresa;
import br.com.sistema.comercio.model.Produto;
import br.com.sistema.comercio.repository.EstoqueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstoqueServiceTest {

    @Mock
    private EstoqueRepository estoqueRepository;

    @InjectMocks
    private EstoqueService estoqueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEstoque() {
        Estoque estoque = new Estoque();
        estoque.setQuantidade(10);
        
        when(estoqueRepository.save(estoque)).thenReturn(estoque);
        
        Estoque result = estoqueService.createEstoque(estoque);
        
        assertNotNull(result);
        assertEquals(10, result.getQuantidade());
        verify(estoqueRepository, times(1)).save(estoque);
    }

    @Test
    void testGetEstoqueById() {
        Estoque estoque = new Estoque();
        estoque.setId(1L);
        estoque.setQuantidade(15);
        
        when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));
        
        Estoque result = estoqueService.getEstoqueById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(15, result.getQuantidade());
        verify(estoqueRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateEstoque() {
        Estoque existingEstoque = new Estoque();
        existingEstoque.setId(1L);
        existingEstoque.setQuantidade(20);
        
        Estoque updatedEstoque = new Estoque();
        updatedEstoque.setQuantidade(30);
        
        when(estoqueRepository.findById(1L)).thenReturn(Optional.of(existingEstoque));
        when(estoqueRepository.save(existingEstoque)).thenReturn(existingEstoque);
        
        Estoque result = estoqueService.updateEstoque(1L, updatedEstoque);
        
        assertNotNull(result);
        assertEquals(30, result.getQuantidade());
        verify(estoqueRepository, times(1)).save(existingEstoque);
    }

    @Test
    void testDeleteEstoque() {
        doNothing().when(estoqueRepository).deleteById(1L);
        
        estoqueService.deleteEstoque(1L);
        
        verify(estoqueRepository, times(1)).deleteById(1L);
    }
}
