# 🔔 Paso 4 – Stream de alertas (`GET /alerts/stream`)

## Objetivo

Emitir alertas activadas cuando el precio supere el umbral definido.

## Subtareas

1. Crear `AlertNotifierPort` y `SimulatedNotifier`
2. Verificar periódicamente cada alerta (Flux.interval)
3. Filtrar alertas activadas (`filter`)
4. Enviar evento vía SSE (Server-Sent Events)
5. Endpoint: `GET /alerts/stream` retorna `text/event-stream`

## Resultado

Cliente conectado recibe alertas en tiempo real si condiciones se cumplen.