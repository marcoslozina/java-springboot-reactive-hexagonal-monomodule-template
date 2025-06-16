
# ✅ Paso 1 – Consultar el precio actual de un activo (`GET /assets/price`)

## 🎯 Objetivo

Implementar un endpoint que permita consultar el precio actual de un activo financiero (como BTC, ETH, etc.) consumiendo una API pública externa (CoinGecko) de manera reactiva, respetando la arquitectura hexagonal.

---

## 🧱 Estructura involucrada

```
src/
├── domain/
│   ├── model/AssetPrice.java
│   └── port/AssetPriceProviderPort.java
├── application/
│   └── AssetService.java
├── infrastructure/
│   ├── adapter/
│   │   ├── input/AssetController.java
│   │   └── output/PriceApiClient.java
│   └── config/WebClientConfig.java
```

---

## 🧩 Subtareas detalladas

### 1. Crear modelo de dominio `AssetPrice`
`domain/model/AssetPrice.java`
```java
package com.example.domain.model;

import java.math.BigDecimal;
import java.time.Instant;

public class AssetPrice {
    private final String symbol;
    private final BigDecimal price;
    private final Instant timestamp;

    public AssetPrice(String symbol, BigDecimal price, Instant timestamp) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getSymbol() { return symbol; }
    public BigDecimal getPrice() { return price; }
    public Instant getTimestamp() { return timestamp; }
}
```

---

### 2. Crear puerto de salida `AssetPriceProviderPort`
`domain/port/AssetPriceProviderPort.java`
```java
package com.example.domain.port;

import com.example.domain.model.AssetPrice;
import reactor.core.publisher.Mono;

public interface AssetPriceProviderPort {
    Mono<AssetPrice> getCurrentPrice(String symbol);
}
```

---

### 3. Implementar adaptador `PriceApiClient`
`infrastructure/adapter/output/PriceApiClient.java`
```java
package com.example.infrastructure.adapter.output;

import com.example.domain.model.AssetPrice;
import com.example.domain.port.AssetPriceProviderPort;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@Component
public class PriceApiClient implements AssetPriceProviderPort {

    private final WebClient webClient;

    public PriceApiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<AssetPrice> getCurrentPrice(String symbol) {
        String id = mapToApiId(symbol);
        return webClient.get()
                .uri("/simple/price?ids={id}&vs_currencies=usd", id)
                .retrieve()
                .bodyToMono(Map.class)
                .map(body -> {
                    Map<String, Object> coin = (Map<String, Object>) body.get(id);
                    BigDecimal price = new BigDecimal(coin.get("usd").toString());
                    return new AssetPrice(symbol.toUpperCase(), price, Instant.now());
                });
    }

    private String mapToApiId(String symbol) {
        return switch (symbol.toUpperCase()) {
            case "BTC" -> "bitcoin";
            case "ETH" -> "ethereum";
            case "SOL" -> "solana";
            default -> throw new IllegalArgumentException("Símbolo no soportado: " + symbol);
        };
    }
}
```

---

### 4. Crear servicio `AssetService`
`application/AssetService.java`
```java
package com.example.application;

import com.example.domain.model.AssetPrice;
import com.example.domain.port.AssetPriceProviderPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AssetService {

    private final AssetPriceProviderPort priceProvider;

    public AssetService(AssetPriceProviderPort priceProvider) {
        this.priceProvider = priceProvider;
    }

    public Mono<AssetPrice> getPrice(String symbol) {
        return priceProvider.getCurrentPrice(symbol);
    }
}
```

---

### 5. Crear controlador REST `AssetController`
`infrastructure/adapter/input/AssetController.java`
```java
package com.example.infrastructure.adapter.input;

import com.example.application.AssetService;
import com.example.domain.model.AssetPrice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/assets/price")
    public Mono<AssetPrice> getPrice(@RequestParam String symbol) {
        return assetService.getPrice(symbol);
    }
}
```

---

### 6. Configurar `WebClient`
`infrastructure/config/WebClientConfig.java`
```java
package com.example.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://api.coingecko.com/api/v3")
                .build();
    }
}
```

---

## ✅ Resultado

Llamar a:
```
GET http://localhost:8080/assets/price?symbol=BTC
```
Retorna:
```json
{
  "symbol": "BTC",
  "price": 65840.32,
  "timestamp": "2025-06-16T19:05:34Z"
}
```

---

## 🧪 Test sugerido

- `AssetServiceTest`: mock de `AssetPriceProviderPort`
- `PriceApiClientTest`: test con `WebClient` o WireMock
