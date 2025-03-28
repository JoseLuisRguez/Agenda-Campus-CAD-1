package Agenda.CampusCAD.Controladores;

import Agenda.CampusCAD.Entidades.Usuario;
import Agenda.CampusCAD.Servicios.UsuarioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @PostMapping("")
    public ResponseEntity<?> insertarUsuario(@RequestBody Usuario usuario) {
        if (usuarioService.insertarUsuario(usuario)) {
            return new ResponseEntity<>(usuarioService.obtenerTodosLosUsuarios(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al insertar usuario", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        if (usuarioService.actualizarUsuario(usuario)) {
            return new ResponseEntity<>(usuarioService.obtenerTodosLosUsuarios(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al actualizar usuario", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        if (usuarioService.eliminarUsuario(usuario)) {
            return new ResponseEntity<>(usuarioService.obtenerTodosLosUsuarios(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al eliminar usuario", HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("")
    public List<Usuario> eliminarUsuario(@RequestBody Usuario notificacion) {
        if (usuarioService.eliminarUsuario(notificacion)) {
            return usuarioService.obtenerTodosLosUsuarios();
        } else {
            return null;
        }
    }
    
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        Usuario authenticatedUser = usuarioService.autenticarUsuario(usuario.getEmail(), usuario.getContrasena());
        if (authenticatedUser != null) {
            String token = java.util.UUID.randomUUID().toString(); // Generar un token único
            usuarioService.registrarSesion(authenticatedUser.getId(), token);
            return authenticatedUser;
        }
        return null;
    }

    @PutMapping("/update")
    public Usuario updateUserProfile(@RequestBody Usuario userProfile) {
        return usuarioService.updateUserProfile(userProfile);
    }
    
    @GetMapping("/profile/{id}")
    public ResponseEntity<Usuario> getUserProfile(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUserProfile(id);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/logout/{id}")
    public ResponseEntity<Map<String, String>> logout(@PathVariable Long id) {
        usuarioService.invalidarSesion(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Sesión cerrada correctamente.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/actividad/{id}")
    public ResponseEntity<Map<String, String>> actualizarActividad(@PathVariable Long id) {
        usuarioService.actualizarUltimaActividad(id);

        // Creamos un mapa para devolver la respuesta como JSON
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Actividad actualizada correctamente.");

        // Devolvemos la respuesta en formato JSON con el mensaje
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/inactivos/{minutos}")
    public List<Usuario> obtenerUsuariosInactivos(@PathVariable int minutos) {
        return usuarioService.obtenerUsuariosInactivos(minutos);
    }
 
}
