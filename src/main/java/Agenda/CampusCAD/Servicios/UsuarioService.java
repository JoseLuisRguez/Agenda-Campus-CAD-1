package Agenda.CampusCAD.Servicios;

import Agenda.CampusCAD.Entidades.Usuario;
import Agenda.CampusCAD.Repositorios.UsuarioRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public boolean insertarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            return false; // El usuario ya existe
        }
        usuarioRepository.save(usuario);
        return true;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    public boolean eliminarUsuario(Usuario usuario) {
        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            usuarioRepository.delete(usuario);
            return true;
        }
        return false;
    }

    public Usuario autenticarUsuario(String email, String contrasena) {
        return usuarioRepository.findByEmailAndContrasena(email, contrasena);
    }
    
     public Optional<Usuario> getUserProfile(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario updateUserProfile(Usuario userProfile) {
        return usuarioRepository.save(userProfile);
    }
    
    public Usuario registrarSesion(Long usuarioId, String token) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario != null) {
            usuario.setSessionToken(token);
            usuario.setLastActivity(LocalDateTime.now());
            return usuarioRepository.save(usuario);
        }
        return null;
    }
    
    public void invalidarSesion(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario != null) {
            usuario.setSessionToken(null);
            usuario.setLastActivity(null);
            usuarioRepository.save(usuario);
        }
    }

    public void actualizarUltimaActividad(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario != null) {
            usuario.setLastActivity(LocalDateTime.now());
            usuarioRepository.save(usuario);
        }
    }
    
    public List<Usuario> obtenerUsuariosInactivos(int minutosInactividad) {
        LocalDateTime ahora = LocalDateTime.now();
        return usuarioRepository.findAll().stream()
            .filter(usuario -> usuario.getLastActivity() != null &&
                               Duration.between(usuario.getLastActivity(), ahora).toMinutes() >= minutosInactividad)
            .toList();
    }
    
}
