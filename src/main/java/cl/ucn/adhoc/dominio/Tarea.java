package cl.ucn.adhoc.dominio;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descripcion;
    @Column(name = "fecha_vencimiento")
    private LocalDate fechaFinalizacion;
    private boolean completada;

    public void marcarCompleta() { this.completada = true; }

    public Long getId() {
        return id;
    }

    public Tarea(String Titulo, String descripcion, LocalDate fechaFinalizacion) {
        this.titulo = Titulo;
        this.descripcion = descripcion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.completada = false;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }
    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
    public boolean isCompletada() {
        return completada;
    }
    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}

