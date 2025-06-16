
# 📈 Caso de Estudio: Servicio de Alertas de Precios Financieros (Reactivo + Hexagonal)

## 🎯 Objetivo

Implementar un microservicio backend en Java + Spring Boot que aplique **programación reactiva** y **arquitectura hexagonal**, con foco en el dominio financiero. El servicio permitirá consultar precios de activos y generar **alertas en tiempo real** cuando se superen ciertos umbrales.

---

## 🧩 Características principales

- ✅ Consumo de API externa de precios (acciones, criptos, etc.)
- ✅ Soporte de múltiples usuarios y alertas activas
- ✅ Programación completamente **no bloqueante** con `WebFlux`
- ✅ Estructura basada en **arquitectura hexagonal**
- ✅ Conceptos de **backpressure**, **streaming**, y **resiliencia**
- ✅ Preparado para escalar y extender a persistencia real o notificaciones push

---

## 🧱 Arquitectura

```text
src/
├── domain/
│   ├── model/                ← AssetPrice, PriceAlert
│   └── port/                 ← AssetPriceProviderPort, AlertNotifierPort
├── application/
│   └── AlertService          ← Lógica de negocio para gestión y activación de alertas
├── infrastructure/
│   ├── adapter/
│   │   ├── input/
│   │   │   └── AlertController ← Endpoints REST
│   │   └── output/
│   │       ├── PriceApiClient ← Cliente HTTP externo (WebClient)
│   │       └── SimulatedNotifier ← Notificador de alertas (log, SSE)
│   └── config/               ← WebClient, Scheduler, etc.
└── tests/                    ← Tests con mocks y StepVerifier
```

---

## 📘 Endpoints funcionales (MVP)

| Método | Ruta                     | Descripción |
|--------|--------------------------|-------------|
| `GET`  | `/assets/price?symbol=`  | Devuelve el precio actual de un activo |
| `POST` | `/alerts`                | Registra una alerta personalizada por umbral |
| `GET`  | `/alerts/stream`         | Devuelve alertas activadas en tiempo real vía SSE |
| `GET`  | `/assets/history`        | Historial de precios recientes (in-memory) |

---

## 💡 Conceptos aplicados

### 🔁 Programación reactiva
- `Mono`, `Flux`
- `WebClient` no bloqueante
- `interval`, `zipWhen`, `filter`, `retryWhen`, `timeout`
- `onBackpressureBuffer`, `limitRate`, `Schedulers.boundedElastic()`

### 🧱 Arquitectura hexagonal
- Separación por capas: dominio, aplicación, infraestructura
- Puertos y adaptadores (`Port` + `Client`/`Controller`)
- Inversión de dependencias

### 🧪 Testing
- Mock de puertos (ej: `PriceApiClient`)
- Verificación de flujos (`StepVerifier`)
- Tests unitarios por capa

---

## 🔄 Flujo general

1. Usuario registra una alerta: `POST /alerts`
2. Servicio consulta precios periódicamente vía `WebClient`
3. Si el precio cumple la condición (ej: BTC > 65.000), se emite la alerta
4. El usuario recibe la alerta vía SSE o webhook simulado

---

## 📦 Futuras extensiones (no MVP)

- Persistencia real (ej: Mongo R2DBC, Redis)
- Seguridad (`Keycloak`, tokens)
- Notificaciones reales (Webhook, email, Telegram)
- Panel frontend en React / Angular para visualizar alertas

---

## 📌 API pública sugerida para precios

- [https://www.coingecko.com/en/api](https://www.coingecko.com/en/api) (gratis y sin clave)
- [https://www.alphavantage.co/](https://www.alphavantage.co/) (requiere API Key)
- [https://www.coincap.io/](https://docs.coincap.io/) (streaming de precios)

---

## ✍️ Autor

Este caso de estudio forma parte del proyecto [java-springboot-reactive-hexagonal-monomodule-template](https://github.com/marcoslozina/java-springboot-reactive-hexagonal-monomodule-template) creado por [@marcoslozina](https://github.com/marcoslozina) como demostración de buenas prácticas en backend moderno.

---
