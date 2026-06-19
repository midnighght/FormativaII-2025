// src/main/java/cl/ucn/adhoc/vertx/MainVerticle.java
package cl.ucn.adhoc.vertx;

import io.vertx.core.Vertx;

public class MainVerticle {
    public static void main(String[] args) {
        var vertx = Vertx.vertx();

        vertx.deployVerticle(new RepositorioTareaVerticle());
        vertx.deployVerticle(new ServicioTareaVerticle());
        vertx.deployVerticle(new ClienteVerticle());
    }
}