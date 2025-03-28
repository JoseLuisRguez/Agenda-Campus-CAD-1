package Agenda.CampusCAD.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "reservas")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombrecompleto")
    private String nombreCompleto;

    @Column(name = "tipocancha")
    private String tipoCancha;

    @Column(name = "horario")
    private String horario;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "tiempouso")
    private String tiempoUso;

    @Column(name = "tipopersona")
    private String tipoPersona;

    @Column(name = "preciocancha")
    private Double precioCancha;

    @Column(name = "pago")
    private String pago;
    
    // Nuevo campo
    private Long idusuario;
    
    // Nuevo campo para el estado
    @Column(name = "estado", nullable = false)
    private String estado = "pendiente"; // Valor por defecto

    public Reserva(Long id, String nombreCompleto, String tipoCancha, String horario, Date fecha, String tiempoUso, String tipoPersona, Double precioCancha, String pago, Long idusuario, String estado) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.tipoCancha = tipoCancha;
        this.horario = horario;
        this.fecha = fecha;
        this.tiempoUso = tiempoUso;
        this.tipoPersona = tipoPersona;
        this.precioCancha = precioCancha;
        this.pago = pago;
        this.idusuario = idusuario;
        this.estado = estado; // Asignar el nuevo campo
    }

    // Constructor sin argumentos (requerido por JPA)
    public Reserva() {
    }
    
    // getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTipoCancha() {
        return tipoCancha;
    }

    public void setTipoCancha(String tipoCancha) {
        this.tipoCancha = tipoCancha;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTiempoUso() {
        return tiempoUso;
    }

    public void setTiempoUso(String tiempoUso) {
        this.tiempoUso = tiempoUso;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Double getPrecioCancha() {
        return precioCancha;
    }

    public void setPrecioCancha(Double precioCancha) {
        this.precioCancha = precioCancha;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
