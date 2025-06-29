package com.company.project.templateservice.controller.adapters.in.rest;
import com.company.project.templateservice.adapters.in.rest.SecureController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(SecureController.class)
@ActiveProfiles("test")
class SecureControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void helloSecureShouldReturnMessage() {
        webTestClient.get()
            .uri("/secure/hello")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .isEqualTo("🔐 Hola desde endpoint seguro (con JWT)");
    }
}
