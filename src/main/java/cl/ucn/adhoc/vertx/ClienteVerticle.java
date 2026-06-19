// src/main/java/cl/ucn/adhoc/vertx/ClienteVerticle.java
package cl.ucn.adhoc.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import java.time.LocalDate;

public class ClienteVerticle extends AbstractVerticle {
    static int id;
    @Override
    public void start() {
        // Crear una tarea
        JsonObject nuevaTarea = new JsonObject()
                .put("titulo", "Tarea 1")
                .put("descripcion", "Descripcion 1");

        vertx.eventBus().request("tareas.crear", nuevaTarea, res -> {
            if (res.succeeded()) {
                System.out.println("Tarea creada: " + res.result().body());
                id = ((JsonObject) res.result().body()).getInteger("id");
                // Luego, pedir listar tareas pendientes
                vertx.eventBus().request("tareas.listarPendientes", new JsonObject(), res2 -> {
                    if (res2.succeeded()) {
                        System.out.println("Tareas pendientes: " + res2.result().body());
                        vertx.eventBus().request("tareas.completar", new JsonObject().put("id", id), res3 -> {
                            if (res3.succeeded()) {
                                System.out.println("Respuesta completar: " + res3.result().body());
                            } else {
                                System.out.println("Error al completar tarea");
                            }
                        });
                    } else {
                        System.out.println("Error al listar tareas");
                    }
                });
            } else {
                System.out.println("Error al crear tarea");
            }
        });

        // Completar la tarea
//        vertx.eventBus().request("tareas.completar", new JsonObject().put("id", id), res3 -> {
//            if (res3.succeeded()) {
//                System.out.println("Respuesta completar: " + res3.result().body());
//            } else {
//                System.out.println("Error al completar tarea");
//            }
//        });
    }
}