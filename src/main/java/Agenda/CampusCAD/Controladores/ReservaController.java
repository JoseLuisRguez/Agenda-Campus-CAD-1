package Agenda.CampusCAD.Controladores;

import Agenda.CampusCAD.Entidades.Reserva;
import Agenda.CampusCAD.Repositorios.ReservaRepository;
import Agenda.CampusCAD.Servicios.ReservaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    @GetMapping("")
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaService.obtenerTodasLasReservas();
    }

    @PostMapping("")
    public List<Reserva> insertarReserva(@RequestBody Reserva reserva) {
        if (reservaService.insertarReserva(reserva)) {
            return reservaService.obtenerTodasLasReservas();
        } else {
            return null;
        }
    }

    @PutMapping("")
    public List<Reserva> actualizarReserva(@RequestBody Reserva reserva) {
        if (reservaService.actualizarReserva(reserva)) {
            return reservaService.obtenerTodasLasReservas();
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public List<Reserva> eliminarReserva(@PathVariable Long id) {
        if (reservaService.eliminarReservaPorId(id)) {
            return reservaService.obtenerTodasLasReservas();
        } else {
            return null;
        }
    }
    
    // Obtener reservas por usuario
    @GetMapping("/usuario/{idusuario}")
    public List<Reserva> getReservasByUsuario(@PathVariable Long idusuario) {
        return reservaService.getReservasByUsuario(idusuario);
    }
    
    // Actualizar el estado de una reserva
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Reserva> actualizarEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        Reserva reservaActualizada = reservaService.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(reservaActualizada);
    }
    
}
