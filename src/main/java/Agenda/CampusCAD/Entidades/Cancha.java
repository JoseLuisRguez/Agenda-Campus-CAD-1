package Agenda.CampusCAD.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "canchas")
public class Cancha {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "tipo_deporte")
    private String tipoDeporte;

    @Column(name = "disponibilidad")
    private String disponibilidad;

    @Column(name = "costo_reserva")
    private Integer costoReserva;

    @Column(name = "reglas_especificas", columnDefinition = "TEXT")
    private String reglasEspecificas;
    
    @Column(name = "imagen")
    private String imagen; // Puedes cambiar el tipo de datos según tus necesidades

    public Cancha(Long id, String nombre, String ubicacion, String tipoDeporte, String disponibilidad, Integer costoReserva, String reglasEspecificas, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tipoDeporte = tipoDeporte;
        this.disponibilidad = disponibilidad;
        this.costoReserva = costoReserva;
        this.reglasEspecificas = reglasEspecificas;
        this.imagen = imagen;
    }
    
    // getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipoDeporte() {
        return tipoDeporte;
    }

    public void setTipoDeporte(String tipoDeporte) {
        this.tipoDeporte = tipoDeporte;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Integer getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(Integer costoReserva) {
        this.costoReserva = costoReserva;
    }

    public String getReglasEspecificas() {
        return reglasEspecificas;
    }

    public void setReglasEspecificas(String reglasEspecificas) {
        this.reglasEspecificas = reglasEspecificas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}
