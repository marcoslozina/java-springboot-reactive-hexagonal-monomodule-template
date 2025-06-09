package com.company.project.templateservice.integration;

import com.company.project.templateservice.controller.AdminController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("test")
@WebFluxTest(controllers = AdminController.class)
@Import(TestSecurityConfig.class)
class SecurityIntegrationTest {

  @Autowired private WebTestClient webClient;

  @Test
  @WithMockUser(roles = "ADMIN")
  void adminWithRoleCanAccess() {
    webClient
        .get()
        .uri("/admin/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("Hello Admin 👋");
  }

  @Test
  @WithMockUser(roles = "USER")
  void userWithoutAdminRoleIsDenied() {
    webClient.get().uri("/admin/hello").exchange().expectStatus().isForbidden();
  }
}
