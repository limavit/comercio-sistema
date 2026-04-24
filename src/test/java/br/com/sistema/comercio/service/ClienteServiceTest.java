package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Cliente;
import br.com.sistema.comercio.model.Empresa;
import br.com.sistema.comercio.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;
    
    private Empresa empresa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Criar empresa para testes
        empresa = new Empresa();
        empresa.setId(1L);
        empresa.setNome("Empresa Teste");
        empresa.setCnpj("12345678901234");
        empresa.setAtivo(true);
        empresa.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testCreateCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setTelefone("(11) 1234-5678");
        cliente.setEmail("cliente@teste.com");
        cliente.setEmpresa(empresa);
        cliente.setAtivo(true);
        
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        
        Cliente result = clienteService.createCliente(cliente);
        
        assertNotNull(result);
        assertEquals("Cliente Teste", result.getNome());
        assertEquals("(11) 1234-5678", result.getTelefone());
        assertEquals("cliente@teste.com", result.getEmail());
        assertEquals(empresa, result.getEmpresa());
        assertTrue(result.getAtivo());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testCreateClienteSemNome() {
        Cliente cliente = new Cliente();
        cliente.setNome("");
        cliente.setEmpresa(empresa);
        
        assertThrows(IllegalArgumentException.class, () -> {
            clienteService.createCliente(cliente);
        });
        
        verify(clienteRepository, never()).save(cliente);
    }

    @Test
    void testCreateClienteSemEmpresa() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setEmpresa(null);
        
        assertThrows(IllegalArgumentException.class, () -> {
            clienteService.createCliente(cliente);
        });
        
        verify(clienteRepository, never()).save(cliente);
    }

    @Test
    void testGetClienteById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente Teste");
        cliente.setTelefone("(11) 1234-5678");
        cliente.setEmpresa(empresa);
        
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        
        Cliente result = clienteService.getClienteById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Cliente Teste", result.getNome());
        assertEquals("(11) 1234-5678", result.getTelefone());
        assertEquals(empresa, result.getEmpresa());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateCliente() {
        Cliente existingCliente = new Cliente();
        existingCliente.setId(1L);
        existingCliente.setNome("Cliente Antigo");
        existingCliente.setTelefone("(11) 1111-1111");
        existingCliente.setEmail("antigo@teste.com");
        existingCliente.setEmpresa(empresa);
        existingCliente.setAtivo(true);
        
        Cliente updatedCliente = new Cliente();
        updatedCliente.setNome("Cliente Atualizado");
        updatedCliente.setTelefone("(11) 2222-2222");
        updatedCliente.setEmail("novo@teste.com");
        updatedCliente.setAtivo(false);
        
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(existingCliente));
        when(clienteRepository.save(existingCliente)).thenReturn(existingCliente);
        
        Cliente result = clienteService.updateCliente(1L, updatedCliente);
        
        assertNotNull(result);
        assertEquals("Cliente Atualizado", result.getNome());
        assertEquals("(11) 2222-2222", result.getTelefone());
        assertEquals("novo@teste.com", result.getEmail());
        assertEquals(empresa, result.getEmpresa());
        assertFalse(result.getAtivo());
        verify(clienteRepository, times(1)).save(existingCliente);
    }

    @Test
    void testUpdateClienteParcial() {
        Cliente existingCliente = new Cliente();
        existingCliente.setId(1L);
        existingCliente.setNome("Cliente Antigo");
        existingCliente.setTelefone("(11) 1111-1111");
        existingCliente.setEmail("antigo@teste.com");
        existingCliente.setEmpresa(empresa);
        existingCliente.setAtivo(true);
        
        Cliente updatedCliente = new Cliente();
        updatedCliente.setNome("Cliente Atualizado");
        updatedCliente.setEmail("novo@teste.com");
        
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(existingCliente));
        when(clienteRepository.save(existingCliente)).thenReturn(existingCliente);
        
        Cliente result = clienteService.updateCliente(1L, updatedCliente);
        
        assertNotNull(result);
        assertEquals("Cliente Atualizado", result.getNome());
        assertEquals("(11) 1111-1111", result.getTelefone()); // Não deve mudar
        assertEquals("novo@teste.com", result.getEmail());
        assertEquals(empresa, result.getEmpresa()); // Não deve mudar
        assertTrue(result.getAtivo()); // Não deve mudar
        verify(clienteRepository, times(1)).save(existingCliente);
    }

    @Test
    void testDeleteCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);
        
        clienteService.deleteCliente(1L);
        
        verify(clienteRepository, times(1)).delete(cliente);
    }

    @Test
    void testGetClientesByEmpresa() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");
        cliente1.setEmpresa(empresa);
        
        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Cliente 2");
        cliente2.setEmpresa(empresa);
        
        List<Cliente> clientes = List.of(cliente1, cliente2);
        
        when(clienteRepository.findByEmpresaId(1L)).thenReturn(clientes);
        
        List<Cliente> result = clienteService.getClientesByEmpresa(1L);
        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Cliente 1", result.get(0).getNome());
        assertEquals("Cliente 2", result.get(1).getNome());
        assertEquals(empresa, result.get(0).getEmpresa());
        assertEquals(empresa, result.get(1).getEmpresa());
        verify(clienteRepository, times(1)).findByEmpresaId(1L);
    }

    @Test
    void testGetClientesAtivos() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");
        cliente1.setEmpresa(empresa);
        cliente1.setAtivo(true);
        
        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Cliente 2");
        cliente2.setEmpresa(empresa);
        cliente2.setAtivo(true);
        
        List<Cliente> clientes = List.of(cliente1, cliente2);
        
        when(clienteRepository.findByAtivoTrue()).thenReturn(clientes);
        
        List<Cliente> result = clienteService.getClientesAtivos();
        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Cliente 1", result.get(0).getNome());
        assertEquals("Cliente 2", result.get(1).getNome());
        assertEquals(empresa, result.get(0).getEmpresa());
        assertEquals(empresa, result.get(1).getEmpresa());
        verify(clienteRepository, times(1)).findByAtivoTrue();
    }
}
