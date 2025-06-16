# 📬 Paso 3 – Registro de alertas (`POST /alerts`)

## Objetivo

Permitir registrar alertas por umbral de precio para un activo.

## Subtareas

1. Crear clase `PriceAlert` en dominio
2. Crear puerto `AlertStoragePort` y su implementación in-memory
3. Crear DTO entrada/salida para alerta
4. Crear endpoint POST `/alerts`
5. Validar umbrales y símbolo en el controlador o servicio
6. Guardar alerta asociada a un símbolo

## Resultado esperado

```json
POST /alerts
{
  "symbol": "BTC",
  "threshold": 70000,
  "type": "GREATER_THAN"
}
```