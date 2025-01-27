package Agenda.CampusCAD.Servicios;

import Agenda.CampusCAD.Entidades.Notification;
import Agenda.CampusCAD.Repositorios.NotificationRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;
    
    // Obtener todas las notificaciones
    public List<Notification> obtenerTodasLasNotificaciones() {
        return notificationRepository.findAll();
    }
    
    // Insertar una nueva notificación
    public boolean insertarNotificacion(Notification notification) {
        if (notification.getSubject() == null || notification.getMessage() == null || notification.getSenderId() == null) {
            return false; // Validación de campos obligatorios
        }
        notification.setCreatedAt(LocalDateTime.now()); // Asignar la fecha de creación
        notificationRepository.save(notification); // Guardar la notificación
        return true;
    }

    // Actualizar una notificación existente
    public boolean actualizarNotificacion(Notification notification) {
        if (notificationRepository.findById(notification.getId()).isPresent()) {
            notificationRepository.save(notification);
            return true;
        }
        return false;
    }
    
    // Eliminar una notificación
    public boolean eliminarNotificacion(Notification notification) {
        if (notificationRepository.findById(notification.getId()).isPresent()) {
            notificationRepository.delete(notification);
            return true;
        }
        return false;
    }
    
    // Actualizar el estado de lectura de una notificación
    public boolean actualizarEstadoDeLectura(Long id, boolean read) {
    Optional<Notification> notificationOpt = notificationRepository.findById(id);
    if (notificationOpt.isPresent()) {
        Notification notification = notificationOpt.get();
        notification.setIsRead(read);  // Actualizar el estado de lectura
        notificationRepository.save(notification);  // Guardar la notificación actualizada
        return true;
    }
    return false;
    }

    // Obtener notificación por ID
    public Optional<Notification> obtenerNotificacionPorId(Long id) {
        return notificationRepository.findById(id);
    }
    
    // Filtrar notificaciones por usuario
    public List<Notification> obtenerNotificacionesPorUsuario(String userId) {
        // Primero obtener todas las notificaciones
        List<Notification> allNotifications = notificationRepository.findAll();
        // Filtrar las notificaciones donde el target es "all" o el userId coincide con la notificación
        return allNotifications.stream()
            .filter(notification -> notification.getTarget().equals("all") || notification.getUserId().equals(userId))
            .collect(Collectors.toList());
    }

}
