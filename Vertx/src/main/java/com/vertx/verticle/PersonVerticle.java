package com.vertx.verticle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vertx.imp.PersonImp;
import com.vertx.model.Person;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonVerticle extends AbstractVerticle {

    public static final String GET_PERSON = "get.persons.all";
    public static final String SAVE_PERSON = "save.persons";

    private final ObjectMapper mapper = Json.mapper;

    @Autowired
    private PersonImp personImp;

    @Override
    public void start() throws Exception {
        super.start();
        vertx.eventBus()
                .<String>consumer(GET_PERSON)
                .handler(listPersonImp());

        vertx.eventBus()
                .<String>consumer(SAVE_PERSON)
                .handler(s -> savePersonImp(s.body()));


    }

    private Handler<Message<String>> listPersonImp() {
        return msg -> vertx.<String>executeBlocking(future -> {
            try {
                future.complete(mapper.writeValueAsString(personImp.listPerson()));
            } catch (JsonProcessingException e) {
                System.out.println("Failed to serialize result");
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                msg.reply(result.result());
            } else {
                msg.reply(result.cause()
                        .toString());
            }
        });
    }

    private Handler<Message<String>> savePersonImp(String object) {
        Gson g = new Gson();
        System.out.println(object);
        Person person = g.fromJson(object, Person.class);

        return msg -> vertx.<String>executeBlocking(future -> {
            try {
                future.complete(mapper.writeValueAsString(personImp.savePerson(person)));
            } catch (JsonProcessingException e) {
                System.out.println("Failed to serialize result");
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                msg.reply(result.result());
            } else {
                msg.reply(result.cause()
                        .toString());
            }
        });
    }

}
