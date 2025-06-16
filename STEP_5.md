# 🔄 Paso 5 – Resiliencia y manejo de errores

## Objetivo

Mejorar tolerancia a fallos al consumir APIs externas.

## Subtareas

1. Agregar `.retryWhen(...)` en `PriceApiClient`
2. Agregar `.timeout(...)` y manejo con `.onErrorResume(...)`
3. Crear `GlobalErrorHandler` con `@ControllerAdvice`
4. Loguear errores con `doOnError(...)`

## Resultado

El sistema sigue funcionando ante fallos temporales de red o API externa.