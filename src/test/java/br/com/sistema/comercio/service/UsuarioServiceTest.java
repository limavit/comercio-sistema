package br.com.sistema.comercio.service;

import br.com.sistema.comercio.model.Usuario;
import br.com.sistema.comercio.model.Empresa;
import br.com.sistema.comercio.model.Perfil;
import br.com.sistema.comercio.repository.UsuarioRepository;
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

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;
    
    private Empresa empresa;
    private Perfil perfil;

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
        
        // Criar perfil para testes
        perfil = new Perfil();
        perfil.setId(1L);
        perfil.setNome("Administrador");
    }

    @Test
    void testCreateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        usuario.setEmpresa(empresa);
        usuario.setPerfil(perfil);
        usuario.setAtivo(true);
        
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        
        Usuario result = usuarioService.createUsuario(usuario);
        
        assertNotNull(result);
        assertEquals("João Silva", result.getNome());
        assertEquals("joao@email.com", result.getEmail());
        assertEquals(empresa, result.getEmpresa());
        assertEquals(perfil, result.getPerfil());
        assertTrue(result.getAtivo());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testCreateUsuarioSemNome() {
        Usuario usuario = new Usuario();
        usuario.setNome("");
        usuario.setEmail("joao@email.com");
        usuario.setEmpresa(empresa);
        usuario.setPerfil(perfil);
        
        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.createUsuario(usuario);
        });
        
        verify(usuarioRepository, never()).save(usuario);
    }

    @Test
    void testCreateUsuarioSemEmail() {
        Usuario usuario = new Usuario();
        usuario.setNome("João Silva");
        usuario.setEmail("");
        usuario.setEmpresa(empresa);
        usuario.setPerfil(perfil);
        
        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.createUsuario(usuario);
        });
        
        verify(usuarioRepository, never()).save(usuario);
    }

    @Test
    void testCreateUsuarioSemEmpresa() {
        Usuario usuario = new Usuario();
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        usuario.setEmpresa(null);
        usuario.setPerfil(perfil);
        
        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.createUsuario(usuario);
        });
        
        verify(usuarioRepository, never()).save(usuario);
    }

    @Test
    void testGetUsuarioById() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        usuario.setEmpresa(empresa);
        usuario.setPerfil(perfil);
        usuario.setAtivo(true);
        
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        
        Usuario result = usuarioService.getUsuarioById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("João Silva", result.getNome());
        assertEquals("joao@email.com", result.getEmail());
        assertEquals(empresa, result.getEmpresa());
        assertEquals(perfil, result.getPerfil());
        assertTrue(result.getAtivo());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateUsuario() {
        Usuario existingUsuario = new Usuario();
        existingUsuario.setId(1L);
        existingUsuario.setNome("João Silva");
        existingUsuario.setEmail("joao@email.com");
        existingUsuario.setEmpresa(empresa);
        existingUsuario.setPerfil(perfil);
        existingUsuario.setAtivo(true);
        
        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setNome("João Silva Updated");
        updatedUsuario.setEmail("joao.updated@email.com");
        updatedUsuario.setPerfil(null);
        updatedUsuario.setAtivo(false);
        
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existingUsuario));
        when(usuarioRepository.save(existingUsuario)).thenReturn(existingUsuario);
        
        Usuario result = usuarioService.updateUsuario(1L, updatedUsuario);
        
        assertNotNull(result);
        assertEquals("João Silva Updated", result.getNome());
        assertEquals("joao.updated@email.com", result.getEmail());
        assertEquals(empresa, result.getEmpresa());
        assertNull(result.getPerfil());
        assertFalse(result.getAtivo());
        verify(usuarioRepository, times(1)).save(existingUsuario);
    }

    @Test
    void testUpdateUsuarioParcial() {
        Usuario existingUsuario = new Usuario();
        existingUsuario.setId(1L);
        existingUsuario.setNome("João Silva");
        existingUsuario.setEmail("joao@email.com");
        existingUsuario.setEmpresa(empresa);
        existingUsuario.setPerfil(perfil);
        existingUsuario.setAtivo(true);
        
        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setNome("João Silva Updated");
        
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existingUsuario));
        when(usuarioRepository.save(existingUsuario)).thenReturn(existingUsuario);
        
        Usuario result = usuarioService.updateUsuario(1L, updatedUsuario);
        
        assertNotNull(result);
        assertEquals("João Silva Updated", result.getNome());
        assertEquals("joao@email.com", result.getEmail()); // Não deve mudar
        assertEquals(empresa, result.getEmpresa()); // Não deve mudar
        assertEquals(perfil, result.getPerfil()); // Não deve mudar
        assertTrue(result.getAtivo()); // Não deve mudar
        verify(usuarioRepository, times(1)).save(existingUsuario);
    }

    @Test
    void testDeleteUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioRepository).delete(usuario);
        
        usuarioService.deleteUsuario(1L);
        
        verify(usuarioRepository, times(1)).delete(usuario);
    }

    @Test
    void testGetUsuariosByEmpresa() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNome("João Silva");
        usuario1.setEmail("joao@email.com");
        usuario1.setEmpresa(empresa);
        usuario1.setPerfil(perfil);
        usuario1.setAtivo(true);
        
        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNome("Maria Silva");
        usuario2.setEmail("maria@email.com");
        usuario2.setEmpresa(empresa);
        usuario2.setPerfil(perfil);
        usuario2.setAtivo(true);
        
        List<Usuario> usuarios = List.of(usuario1, usuario2);
        
        when(usuarioRepository.findByEmpresaId(1L)).thenReturn(usuarios);
        
        List<Usuario> result = usuarioService.getUsuariosByEmpresa(1L);
        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("João Silva", result.get(0).getNome());
        assertEquals("Maria Silva", result.get(1).getNome());
        assertEquals(empresa, result.get(0).getEmpresa());
        assertEquals(empresa, result.get(1).getEmpresa());
        verify(usuarioRepository, times(1)).findByEmpresaId(1L);
    }

    @Test
    void testGetUsuariosAtivos() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNome("João Silva");
        usuario1.setEmail("joao@email.com");
        usuario1.setEmpresa(empresa);
        usuario1.setPerfil(perfil);
        usuario1.setAtivo(true);
        
        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNome("Maria Silva");
        usuario2.setEmail("maria@email.com");
        usuario2.setEmpresa(empresa);
        usuario2.setPerfil(perfil);
        usuario2.setAtivo(true);
        
        List<Usuario> usuarios = List.of(usuario1, usuario2);
        
        when(usuarioRepository.findByAtivoTrue()).thenReturn(usuarios);
        
        List<Usuario> result = usuarioService.getUsuariosAtivos();
        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("João Silva", result.get(0).getNome());
        assertEquals("Maria Silva", result.get(1).getNome());
        assertEquals(empresa, result.get(0).getEmpresa());
        assertEquals(empresa, result.get(1).getEmpresa());
        verify(usuarioRepository, times(1)).findByAtivoTrue();
    }

    @Test
    void testGetUsuariosByPerfil() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNome("João Silva");
        usuario1.setEmail("joao@email.com");
        usuario1.setEmpresa(empresa);
        usuario1.setPerfil(perfil);
        usuario1.setAtivo(true);
        
        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNome("Maria Silva");
        usuario2.setEmail("maria@email.com");
        usuario2.setEmpresa(empresa);
        usuario2.setPerfil(perfil);
        usuario2.setAtivo(true);
        
        List<Usuario> usuarios = List.of(usuario1, usuario2);
        
        when(usuarioRepository.findByPerfilId(1L)).thenReturn(usuarios);
        
        List<Usuario> result = usuarioService.getUsuariosByPerfil(1L);
        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("João Silva", result.get(0).getNome());
        assertEquals("Maria Silva", result.get(1).getNome());
        assertEquals(perfil, result.get(0).getPerfil());
        assertEquals(perfil, result.get(1).getPerfil());
        verify(usuarioRepository, times(1)).findByPerfilId(1L);
    }
}
