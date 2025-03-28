package Agenda.CampusCAD.Controladores;

import Agenda.CampusCAD.Entidades.Notification;
import Agenda.CampusCAD.Servicios.NotificationService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    
    @Autowired
    private NotificationService notificationService;

    // Obtener todas las notificaciones
    @GetMapping("")
    public List<Notification> obtenerTodasLasNotificaciones() {
        return notificationService.obtenerTodasLasNotificaciones();
    }
    
    // Insertar una nueva notificación
    @PostMapping("")
    public ResponseEntity<?> insertarNotificacion(@RequestBody Notification notification) {
        if (notificationService.insertarNotificacion(notification)) {
            return new ResponseEntity<>(notificationService.obtenerTodasLasNotificaciones(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al insertar notificación. Revisa los campos obligatorios.", HttpStatus.BAD_REQUEST);
        }
    }

    
    // Actualizar una notificación por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarNotificacion(@PathVariable Long id, @RequestBody Notification notification) {
        notification.setId(id);
        if (notificationService.actualizarNotificacion(notification)) {
            return new ResponseEntity<>(notificationService.obtenerTodasLasNotificaciones(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al actualizar notificación. No se encontró el ID especificado.", HttpStatus.NOT_FOUND);
        }
    }
    
    // Eliminar una notificación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarNotificacion(@PathVariable Long id) {
        Notification notification = new Notification();
        notification.setId(id);
        if (notificationService.eliminarNotificacion(notification)) {
            return new ResponseEntity<>(notificationService.obtenerTodasLasNotificaciones(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al eliminar notificación. No se encontró el ID especificado.", HttpStatus.NOT_FOUND);
        }
    }
      
    // Obtener una notificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerNotificacionPorId(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.obtenerNotificacionPorId(id);
        if (notification.isPresent()) {
            return new ResponseEntity<>(notification.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Notificación no encontrada.", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/user/{id}")
    public List<Notification> obtenerNotificacionesPorUsuario(@PathVariable String id) {
        return notificationService.obtenerTodasLasNotificaciones().stream()
                .filter(notificacion -> notificacion.getTarget().equals("all") || id.equals(notificacion.getUserId()))
                .collect(Collectors.toList());
    }
    
    // Marcar una notificación como leída o no leída
    @PutMapping("/read/{id}")
    public ResponseEntity<?> actualizarEstadoDeLectura(@PathVariable Long id, @RequestParam boolean read) {
        if (notificationService.actualizarEstadoDeLectura(id, read)) {
            return new ResponseEntity<>("Notificación actualizada correctamente.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo actualizar la notificación.", HttpStatus.NOT_FOUND);
        }
    }
    
}
