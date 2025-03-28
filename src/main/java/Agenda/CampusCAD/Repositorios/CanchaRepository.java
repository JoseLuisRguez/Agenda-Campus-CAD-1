package Agenda.CampusCAD.Repositorios;

import Agenda.CampusCAD.Entidades.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {
    // Puedes agregar métodos adicionales de consulta si es necesario
}
