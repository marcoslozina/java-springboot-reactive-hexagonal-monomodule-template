# 🚨 FRONT_STEP_5 – Visualizar alertas activadas en tiempo real

## Objetivo

Conectarse al stream de alertas activadas usando Server-Sent Events (SSE).

## Subtareas

1. Crear hook `useAlertStream()`
2. Conectarse a `GET /alerts/stream` usando `EventSource`
3. Mostrar mensajes entrantes en tiempo real
4. Manejar reconexiones y errores

## Resultado

Vista de alertas reactivas que se activan en tiempo real desde backend.