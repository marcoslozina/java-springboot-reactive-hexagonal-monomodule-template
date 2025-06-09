package com.company.templateservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class AccessLoggingFilter implements WebFilter {

  private static final Logger log = LoggerFactory.getLogger(AccessLoggingFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    String path = exchange.getRequest().getURI().getPath();
    String method =
        exchange.getRequest().getMethod() != null
            ? exchange.getRequest().getMethod().name()
            : "UNKNOWN";
    String ip =
        exchange.getRequest().getRemoteAddress() != null
            ? exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
            : "unknown";

    return ReactiveSecurityContextHolder.getContext()
        .map(
            ctx -> {
              Authentication auth = ctx.getAuthentication();
              String username = auth != null ? auth.getName() : "anonymous";

              log.info("Access - [{}] {} from {} by user {}", method, path, ip, username);
              return ctx;
            })
        .then(chain.filter(exchange));
  }
}
