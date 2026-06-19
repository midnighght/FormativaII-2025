package cl.ucn.adhoc.repositorio;

import cl.ucn.adhoc.dominio.Tarea;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class RepositorioTareaImpl implements RepositorioTarea {

    @PersistenceContext
    private EntityManager em;

    public RepositorioTareaImpl(EntityManager em) {
        this.em = em;
    }

    public RepositorioTareaImpl() {}

    @Override
    public Tarea guardar(Tarea tarea) {
        if (tarea.getId() == null)
            em.persist(tarea);
        else
            tarea = em.merge(tarea);

        return tarea;
    }

    @Override
    public List<Tarea> encontrarTodas() {
        return em.createQuery("SELECT t FROM Tarea t", Tarea.class).getResultList();

    }

    @Override
    public List<Tarea> encontrarCompletas(boolean status) {
        return List.of();
    }

    @Override
    public void eliminar(Long id) {

    }
}
