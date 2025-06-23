package com.company.project.templateservice.adapters.in.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/hello")
    public Mono<String> helloPublic() {
        return Mono.just("👋 Hola desde endpoint público (sin auth)");
    }
}
