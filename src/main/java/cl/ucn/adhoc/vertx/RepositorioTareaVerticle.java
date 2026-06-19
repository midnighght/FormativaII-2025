// src/main/java/cl/ucn/adhoc/vertx/RepositorioTareaVerticle.java
package cl.ucn.adhoc.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTareaVerticle extends AbstractVerticle {
    private final List<JsonObject> tareas = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void start() {
        vertx.eventBus().consumer("tareas.crear", message -> {
            JsonObject body = (JsonObject) message.body();
            JsonObject tarea = new JsonObject()
                    .put("id", nextId++)
                    .put("titulo", body.getString("titulo"))
                    .put("descripcion", body.getString("descripcion"))
                    .put("fecha", new JsonObject().put("fecha", LocalDate.now().toString()))
                    .put("completada", false)
                    .put("estado", "ok");
            tareas.add(tarea);
            message.reply(new JsonObject().put("estado", tarea.getString("estado")).put("id", tarea.getInteger("id")));
        });

        vertx.eventBus().consumer("tareas.completar", message -> {
            int id = ((JsonObject) message.body()).getInteger("id");
            boolean found = false;
            for (JsonObject tarea : tareas) {
                if (tarea.getInteger("id") == id) {
                    tarea.put("completada", true);
                    found = true;
                    break;
                }
            }
            message.reply(new JsonObject().put("estado", found ? "ok" : "no_encontrada"));
        });

        vertx.eventBus().consumer("tareas.listarPendientes", message -> {
            JsonArray pendientes = new JsonArray();
            for (JsonObject tarea : tareas) {
                if (!tarea.getBoolean("completada", false)) {
                    pendientes.add(tarea);
                }
            }
            message.reply(pendientes);
        });
    }
}