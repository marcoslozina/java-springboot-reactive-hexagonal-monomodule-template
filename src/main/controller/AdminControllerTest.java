package com.company.project.templateservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = AdminController.class)
@Import(TestSecurityConfig.class)
class AdminControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnHelloAdmin() {
        webTestClient
            .get()
            .uri("/admin/hello")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .isEqualTo("Hello Admin 👋");
    }
}
