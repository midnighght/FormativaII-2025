package cl.ucn.adhoc.servicio;

import cl.ucn.adhoc.dominio.Tarea;
import cl.ucn.adhoc.repositorio.RepositorioTarea;

import java.time.LocalDate;
import java.util.List;

public class ServicioTarea {

    private final RepositorioTarea repo;

    public ServicioTarea(RepositorioTarea repo) {
        this.repo = repo;
    }

    public Tarea crearTarea(String title, String desc, LocalDate due) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo");
        }
        Tarea tarea = new Tarea(title, desc, due);
        return repo.guardar(tarea);
    }

    public List<Tarea> obtenerTareas() {
        return repo.encontrarTodas();
    }

    public List<Tarea> obtenerTareasPendientes() {
        return repo.encontrarCompletas(false);
    }

    public void marcarCompletada(Long id) {
        List<Tarea> todas = repo.encontrarTodas();
        for (Tarea t : todas) {
            if (t.getId().equals(id)) {
                t.marcarCompleta();
                repo.guardar(t);
                break;
            }
        }
    }

    public void deleteTask(Long id) {
        repo.eliminar(id);
    }
}


