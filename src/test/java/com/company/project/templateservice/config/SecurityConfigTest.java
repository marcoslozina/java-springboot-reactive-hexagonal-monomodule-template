package com.company.project.templateservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class SecurityConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void publicEndpointAccessibleWithoutAuth() {
        webTestClient.get()
            .uri("/swagger-ui.html")
            .exchange()
            .expectStatus().is3xxRedirection()
            .expectHeader().valueMatches("Location", ".*/webjars/swagger-ui/index.html.*");
    }

    @Test
    void secureEndpointUnauthorizedWithoutToken() {
        webTestClient.get()
            .uri("/secure/hello")
            .exchange()
            .expectStatus().isUnauthorized();
    }
}
