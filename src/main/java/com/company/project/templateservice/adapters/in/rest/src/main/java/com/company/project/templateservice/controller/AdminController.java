package com.company.project.templateservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin")
public class AdminController {

  @GetMapping("/hello")
  @Operation(summary = "Obtener todos los usuarios")
  @ApiResponse(responseCode = "200", description = "Lista de usuarios")
  public Mono<String> helloAdmin() {
    return Mono.just("Hello Admin 👋");
  }
}
