package Agenda.CampusCAD.Servicios;

import Agenda.CampusCAD.Entidades.Cancha;
import Agenda.CampusCAD.Repositorios.CanchaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanchaService {

    @Autowired
    private CanchaRepository canchaRepository;

    //Ahora ejecutamos los metodos
    public List<Cancha> obtenerTodasLasCanchas() {
        return canchaRepository.findAll();
    }
 
    public boolean insertarCancha(Cancha cancha) {
        try {
            canchaRepository.save(cancha);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean actualizarCancha(Cancha cancha) {
        try {
            canchaRepository.save(cancha);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean eliminarCancha(Cancha cancha) {
        try {
            canchaRepository.delete(cancha);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
}
