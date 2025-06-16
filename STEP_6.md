# 🧪 Paso 6 – Testing reactivo

## Objetivo

Asegurar el correcto funcionamiento de la lógica del backend utilizando pruebas unitarias y de integración con enfoque reactivo.

---

## Subtareas

1. Crear test unitario de `AssetService` usando `@MockBean` o Mockito
2. Crear test unitario de `AlertService` con mocks de `AlertStoragePort` y `AssetPriceProviderPort`
3. Usar `StepVerifier` para validar `Mono` y `Flux`
4. Simular errores, demoras y flujos vacíos
5. Crear test de integración con `WebClient` (puede usarse `WireMock` o API real si es estable)

---

## Resultado

Test suite robusta, con alta cobertura de lógica de dominio y flujos reactivos.