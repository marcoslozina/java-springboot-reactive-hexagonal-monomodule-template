# 🧾 Paso 2 – Historial de precios (`GET /assets/history`)

## Objetivo

Registrar en memoria los últimos N precios obtenidos por activo y exponerlos a través de un endpoint reactivo.

## Subtareas

1. Crear una estructura de almacenamiento in-memory (ConcurrentHashMap + List)
2. Almacenar cada respuesta obtenida desde PriceApiClient en esa estructura
3. Crear método en `AssetService` para obtener histórico
4. Crear endpoint REST `GET /assets/history?symbol=BTC`
5. Utilizar `Flux.fromIterable(...)` para retornar lista

## Resultado

```json
[
  { "symbol": "BTC", "price": 65840.32, "timestamp": "..." },
  ...
]
```