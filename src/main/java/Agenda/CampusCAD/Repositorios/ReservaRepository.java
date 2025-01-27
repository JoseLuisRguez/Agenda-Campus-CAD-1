package Agenda.CampusCAD.Repositorios;

import Agenda.CampusCAD.Entidades.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Puedes agregar métodos adicionales de consulta si es necesario
    List<Reserva> findByIdusuario(Long idusuario);
}
