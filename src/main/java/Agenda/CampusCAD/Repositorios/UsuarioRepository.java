package Agenda.CampusCAD.Repositorios;

import Agenda.CampusCAD.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Puedes agregar métodos adicionales de consulta si es necesario
    public Usuario findByEmailAndContrasena(String email, String contrasena);

    public Object findByEmail(String email);
    
    // Nuevo: Buscar usuario por token de sesión
    Usuario findBySessionToken(String sessionToken);
    
}
