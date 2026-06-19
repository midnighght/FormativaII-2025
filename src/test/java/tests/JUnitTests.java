package tests;

import cl.ucn.adhoc.dominio.Tarea;
import cl.ucn.adhoc.repositorio.RepositorioTarea;
import cl.ucn.adhoc.repositorio.RepositorioTareaImpl;
import cl.ucn.adhoc.servicio.ServicioTarea;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class JUnitTests {

    private ServicioTarea servicio;

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
        RepositorioTarea repo = new RepositorioTareaImpl(emf.createEntityManager());
        servicio = new ServicioTarea(repo);
    }

    @Test
    public void testCrearTareaValida() {
        Tarea tarea = servicio.crearTarea("Tarea 1", "Descrip. 1", LocalDate.now());
        assertNotNull(tarea.getId());
        assertEquals("Tarea 1", tarea.getTitulo());
        assertEquals("Descrip. 1", tarea.getDescripcion());
        assertFalse(tarea.isCompletada());
    }

    @Test
    public void testCrearTareaFechaVacia() {
        Tarea tarea = servicio.crearTarea("Tarea 2", "Descrip. 2", null);
        assertNull(tarea.getFechaFinalizacion());
    }

    @Test
    public void testListarTareasVacias() {
        List<Tarea> tareas = servicio.obtenerTareas();
        assertTrue(tareas.isEmpty());
    }

    @Test
    public void testMarcarCompletada() {
        Tarea tarea = servicio.crearTarea("Tarea 4", "Descrip. 4", LocalDate.now());
        servicio.marcarCompletada(tarea.getId());
        assertTrue(tarea.isCompletada());
        List<Tarea> pendientes = servicio.obtenerTareasPendientes();
        assertTrue(pendientes.isEmpty());
    }

    @Test
    public void testEliminarTarea() {
        Tarea tarea = servicio.crearTarea("Tarea 5", "Descrip. 5", LocalDate.now());
        servicio.deleteTask(tarea.getId());
        List<Tarea> tareas = servicio.obtenerTareas();
        assertTrue(tareas.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearTareaNulo() {
        servicio.crearTarea(null, null, null);
    }
}
