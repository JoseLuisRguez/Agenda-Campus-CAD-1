package Agenda.CampusCAD.Controladores;

import Agenda.CampusCAD.Entidades.Cancha;
import Agenda.CampusCAD.Servicios.CanchaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/canchas")
public class CanchaController {
    
    @Autowired
    private CanchaService canchaService;

    @GetMapping("")
    public List<Cancha> obtenerTodasLasCanchas() {
        return canchaService.obtenerTodasLasCanchas();
    }

    @PostMapping("")
    public List<Cancha> insertarCancha(@RequestBody Cancha cancha) {
        if (canchaService.insertarCancha(cancha)) {
            return canchaService.obtenerTodasLasCanchas();
        } else {
            return null;
        }
    }

    @PutMapping("")
    public List<Cancha> actualizarCancha(@RequestBody Cancha cancha) {
        if (canchaService.actualizarCancha(cancha)) {
            return canchaService.obtenerTodasLasCanchas();
        } else {
            return null;
        }
    }

    @DeleteMapping("")
    public List<Cancha> eliminarCancha(@RequestBody Cancha cancha) {
        if (canchaService.eliminarCancha(cancha)) {
            return canchaService.obtenerTodasLasCanchas();
        } else {
            return null;
        }
    }
    
}
