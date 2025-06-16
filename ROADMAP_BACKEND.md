
# 🗺️ Roadmap del Caso de Estudio – Servicio de Alertas Financieras Reactivo

Este documento describe los pasos secuenciales para implementar el caso de estudio aplicando **programación reactiva** y **arquitectura hexagonal** con Java + Spring Boot WebFlux.

---

## ✅ Resumen de pasos

| Paso | Título                                                            | Conceptos clave                                                      |
|------|-------------------------------------------------------------------|-----------------------------------------------------------------------|
| 1️⃣   | Obtener precio actual de un activo (`GET /assets/price`)         | `WebClient`, `Mono`, puerto de salida, modelo dominio                |
| 2️⃣   | Exponer historial in-memory de precios (`GET /assets/history`)   | `Flux`, almacenamiento reactivo in-memory, transformación            |
| 3️⃣   | Registrar alerta por precio (`POST /alerts`)                     | Validación, lógica de dominio, DTOs, entrada hexagonal               |
| 4️⃣   | Emitir alertas vía stream (`GET /alerts/stream`)                 | `Flux`, `interval`, `filter`, SSE, backpressure                      |
| 5️⃣   | Manejo de errores y resiliencia (timeouts, retries)              | `timeout`, `retryWhen`, `onErrorResume`, manejo global de errores    |
| 6️⃣   | Tests reactivos y de integración                                 | `StepVerifier`, mocks de puertos, pruebas con `WebClient` o WireMock |
| 7️⃣   | Refactor y mejoras: caché, métricas y observabilidad             | `doOnNext`, cache reactivo, logs, Micrometer                         |
| 8️⃣   | (Opcional) Notificaciones reales (Webhook, Telegram, etc.)       | Puerto de salida + estrategia de notificación                        |
| 9️⃣   | (Opcional) Seguridad (JWT, Keycloak)                             | Seguridad reactiva con filtros y autorización por token              |

---

## 🧭 Orden sugerido de implementación

1. **Paso 1:** Consulta de precio actual
2. **Paso 2:** Historial de precios (simulado o con cache local)
3. **Paso 3:** Registro de alertas (persistencia in-memory)
4. **Paso 4:** Emisión de alertas vía SSE o logs
5. **Paso 5:** Resiliencia, manejo de errores externos
6. **Paso 6:** Testing unitario y reactivo
7. **Paso 7:** Observabilidad y performance
8. **Paso 8-9:** Seguridad y notificaciones reales

---

## 📌 Notas

- El sistema será 100% reactivo, no bloqueante.
- Todas las funcionalidades respetan la arquitectura hexagonal (puertos y adaptadores).
- El proyecto puede extenderse fácilmente a persistencia real, dashboards o integración con frontend.

---

## ✍️ Autor

Este roadmap forma parte del proyecto [java-springboot-reactive-hexagonal-monomodule-template](https://github.com/marcoslozina/java-springboot-reactive-hexagonal-monomodule-template).
