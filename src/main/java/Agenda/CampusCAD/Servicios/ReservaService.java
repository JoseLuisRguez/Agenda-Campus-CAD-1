package Agenda.CampusCAD.Servicios;

import Agenda.CampusCAD.Entidades.Reserva;
import Agenda.CampusCAD.Repositorios.ReservaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    //Ahora ejecutamos los metodos
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }
    
    public List<Reserva> getReservasByUsuario(Long idusuario) {
        return reservaRepository.findByIdusuario(idusuario);
    }
    
    public boolean insertarReserva(Reserva reserva) {
        try {
            reservaRepository.save(reserva);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean actualizarReserva(Reserva reserva) {
        try {
            reservaRepository.save(reserva);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean eliminarReservaPorId(Long reservaId) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(reservaId);
        if (reservaOptional.isPresent()) {
            reservaRepository.delete(reservaOptional.get());
            return true;
        }
        return false;
    }
    
    // Actualizar el estado de una reserva
    public Reserva actualizarEstado(Long id, String nuevoEstado) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));

        reserva.setEstado(nuevoEstado); // Actualizar el estado
        return reservaRepository.save(reserva); // Guardar los cambios
    }
    
}
