// src/main/java/cl/ucn/adhoc/vertx/ServicioTareaVerticle.java
package cl.ucn.adhoc.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

public class ServicioTareaVerticle extends AbstractVerticle {
    @Override
    public void start() {
//        vertx.eventBus().consumer("servicio.tareas.crear", msg -> {
//            JsonObject datos = (JsonObject) msg.body();
//            vertx.eventBus().request("tareas.crear", datos, reply -> {
//                msg.reply(reply.result().body());
//            });
//        });
//
//        vertx.eventBus().consumer("servicio.tareas.completar", msg -> {
//            JsonObject datos = (JsonObject) msg.body();
//            vertx.eventBus().request("tareas.completar", datos, reply -> {
//                msg.reply(reply.result().body());
//            });
//        });
//
//        vertx.eventBus().consumer("servicio.tareas.listarPendientes", msg -> {
//            vertx.eventBus().request("tareas.listar", new JsonObject(), reply -> {
//                JsonArray todas = (JsonArray) reply.result().body();
//                JsonArray pendientes = new JsonArray();
//                for (int i = 0; i < todas.size(); i++) {
//                    JsonObject tarea = todas.getJsonObject(i);
//                    if (!tarea.getBoolean("completada", false)) {
//                        pendientes.add(tarea);
//                    }
//                }
//                msg.reply(pendientes);
//            });
//        });
    }
}