package com.company.project.templateservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void mainRunsWithoutExceptions() {
        webTestClient.get()
            .uri("/public/hello")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class).isEqualTo("👋 Hola desde endpoint público (sin auth)");
    }
}
