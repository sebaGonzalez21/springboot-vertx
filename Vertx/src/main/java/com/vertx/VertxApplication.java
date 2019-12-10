package com.vertx;

import com.vertx.controller.ControllerPerson;
import com.vertx.verticle.PersonVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Configuration
@EnableJpaRepositories("com.vertx.repository")
@EntityScan("com.vertx.model")
@ComponentScan(basePackages = { "com.vertx" })
public class VertxApplication {

	@Autowired
	private ControllerPerson configurationVertx;

	@Autowired
	private PersonVerticle personRecipient;

	public static void main(String[] args) {
		SpringApplication.run(VertxApplication.class, args);
	}

	@PostConstruct
	public void deployVerticle() {
		final Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(configurationVertx);
		vertx.deployVerticle(personRecipient);
	}
}
