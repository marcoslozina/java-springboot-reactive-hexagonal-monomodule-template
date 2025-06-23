package com.company.project.templateservice.adapters.in.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @GetMapping("/hello")
    public Mono<String> helloSecure() {
        return Mono.just("🔐 Hola desde endpoint seguro (con JWT)");
    }
}
