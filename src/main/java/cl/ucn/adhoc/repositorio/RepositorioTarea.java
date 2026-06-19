package cl.ucn.adhoc.repositorio;

import cl.ucn.adhoc.dominio.Tarea;

import java.util.List;

public interface RepositorioTarea {

    Tarea guardar(Tarea task);
    List<Tarea> encontrarTodas();
    List<Tarea> encontrarCompletas(boolean status);
    void eliminar(Long id);

}
