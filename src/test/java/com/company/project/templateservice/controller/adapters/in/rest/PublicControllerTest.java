package com.company.project.templateservice.controller.adapters.in.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PublicControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void helloPublicReturnsExpectedMessage() {
        webTestClient.get()
            .uri("/public/hello")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .isEqualTo("👋 Hola desde endpoint público (sin auth)");
    }
}
