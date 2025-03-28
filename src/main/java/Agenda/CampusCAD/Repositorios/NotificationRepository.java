package Agenda.CampusCAD.Repositorios;

import Agenda.CampusCAD.Entidades.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Obtener todas las notificaciones de un usuario específico
    List<Notification> findByUserId(String userId);
    
}
