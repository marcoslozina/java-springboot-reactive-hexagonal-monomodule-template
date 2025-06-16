# 📊 Paso 7 – Observabilidad y mejoras

## Objetivo

Agregar trazabilidad, logs detallados y métricas para monitorear el comportamiento de la app en tiempo real.

---

## Subtareas

1. Agregar `doOnNext`, `doOnError`, `doFinally` para seguimiento de eventos en servicios
2. Configurar logs con `logback-spring.xml` o `application.yml`
3. Integrar `Micrometer` para métricas de:
   - Tiempo de respuesta
   - Cantidad de alertas activadas
   - Promedio de precios consultados
4. Exponer métricas en `/actuator/prometheus`
5. (Opcional) Integrar con Prometheus + Grafana en local

---

## Resultado

Sistema observable, fácil de depurar y monitorear en entornos reales o productivos.