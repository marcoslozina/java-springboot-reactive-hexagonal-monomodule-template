package com.company.project.templateservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("local") // 👈️ fuerza el perfil local
class LocalSecurityConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void anyEndpointAccessibleWithLocalProfile() {
        webTestClient.get()
            .uri("/secure/hello")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .isEqualTo("🔐 Hola desde endpoint seguro (con JWT)");
    }
}
