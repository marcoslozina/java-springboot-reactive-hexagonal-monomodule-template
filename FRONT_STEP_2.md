# 📈 FRONT_STEP_2 – Mostrar precio actual de un activo

## Objetivo

Crear un componente que consuma `GET /assets/price?symbol=BTC` y muestre el valor actual.

## Subtareas

1. Crear hook `useAssetPrice(symbol: string)`
2. Crear componente `<PriceViewer />`
3. Usar `axios` para llamar a la API
4. Mostrar `symbol`, `price`, `timestamp`
5. Manejar loading y errores

## Resultado

Precio actualizado mostrado en tiempo real desde backend reactivo.