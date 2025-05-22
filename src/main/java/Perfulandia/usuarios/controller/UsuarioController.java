package Perfulandia.usuarios.controller;

import Perfulandia.usuarios.model.Usuario;
import Perfulandia.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.finAll(); // Cambiado a finAll()
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id); // Cambiado a findById()
        return usuario != null 
                ? ResponseEntity.ok(usuario) 
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.save(usuario); // Sin cambios
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.delete(id); // Sin cambios
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
    Usuario usuarioExistente = usuarioService.findById(id);
    if (usuarioExistente == null) {
        return ResponseEntity.notFound().build();
    }
    // Actualiza los campos necesarios
    usuarioExistente.setNombre(usuarioActualizado.getNombre());
    usuarioExistente.setEmail(usuarioActualizado.getEmail());
    usuarioExistente.setActivo(usuarioActualizado.getActivo());

    Usuario usuarioGuardado = usuarioService.save(usuarioExistente);
    return ResponseEntity.ok(usuarioGuardado);
}

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<List<Usuario>> buscarPorNombre(@RequestParam String nombre) {
        List<Usuario> usuarios = usuarioService.buscarPorNombre(nombre);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuarios inactivos
    @GetMapping("/inactivos")
    public ResponseEntity<List<Usuario>> getUsuariosInactivos() {
        List<Usuario> usuariosInactivos = usuarioService.getUsuariosInactivos();
        if (usuariosInactivos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuariosInactivos);
    }


}

