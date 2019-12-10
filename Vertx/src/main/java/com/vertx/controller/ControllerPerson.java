package com.vertx.controller;

import com.vertx.verticle.PersonVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.springframework.stereotype.Component;

@Component
public class ControllerPerson extends AbstractVerticle {

    //Sobrescribimos metodos para iniciar vertx
    @Override
    public void start() throws Exception {
        super.start();

        Router router = Router.router(vertx);
        //añade handler create a routing context
        router.route().handler(BodyHandler.create());

        router.get("/api/v1/persons")
                .produces("application/json")
                .handler(this::getPersonsHandler);


        router.post("/api/v1/persons")
                .produces("application/json")
                .handler(this::savePersonsHandler);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(config().getInteger("http.port", 8080));
    }

    private void getPersonsHandler(RoutingContext routingContext) {
        vertx.eventBus().<String>send(PersonVerticle.GET_PERSON, "",
                result -> {
                    if (result.succeeded()) {
                        routingContext.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(result.result()
                                        .body());
                    } else {
                        routingContext.response()
                                .setStatusCode(500)
                                .end();
                    }
                });
    }

    private void savePersonsHandler(RoutingContext routingContext) {
        vertx.eventBus().<String>send(PersonVerticle.SAVE_PERSON, routingContext.getBodyAsString(),
                result -> {
                    if (result.succeeded()) {
                        routingContext.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(result.result()
                                        .body());
                    } else {
                        routingContext.response()
                                .setStatusCode(500)
                                .end();
                    }
                });
    }
}
