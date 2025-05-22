package Perfulandia.usuarios.Service;

import Perfulandia.usuarios.model.Usuario;
import Perfulandia.usuarios.repository.UsuarioRepository;
import Perfulandia.usuarios.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

private UsuarioRepository usuarioRepository;
private UsuarioService usuarioService; 

@BeforeEach
void setUp() {
    usuarioRepository = mock(UsuarioRepository.class);
    usuarioService = new UsuarioService(usuarioRepository);
}

@Test
void testBuscarPorNombre() {
    Usuario usuario = new Usuario(1L, "Pedro", "pedro@example.com", true);
    when(usuarioRepository.findByNombre("Pedro")).thenReturn(Arrays.asList(usuario));

    List<Usuario> resultado = usuarioService.buscarPorNombre("Pedro");

    assertEquals(1, resultado.size());
    assertEquals("Pedro", resultado.get(0).getNombre());
}

@Test
void testGetUsuariosInactivos() {
    Usuario inactivo = new Usuario(2L, "Ana", "ana@example.com", false);
    when(usuarioRepository.findByActivoFalse()).thenReturn(Arrays.asList(inactivo));

    List<Usuario> resultado = usuarioService.getUsuariosInactivos();

    assertEquals(1, resultado.size());
    assertFalse(resultado.get(0).getActivo());
}
}
