# 📤 Paso 8 – Notificaciones reales (Webhook / Telegram)

## Objetivo

Implementar el envío real de notificaciones al activarse una alerta, usando Webhook o integración con plataformas como Telegram.

---

## Subtareas

1. Crear puerto de salida `AlertNotificationPort` en el dominio
2. Implementar adaptador `WebhookNotifier` que haga `POST` a un endpoint externo
3. (Opcional) Crear `TelegramNotifier` con token de bot y chat ID
4. Usar `WebClient.post()` en adaptador
5. Llamar al puerto desde `AlertService` cuando una alerta se activa
6. Configurar los destinos desde `application.yml`

---

## Resultado

Cuando se activa una alerta, el sistema envía automáticamente una notificación a un destino configurado (Webhook o chat).