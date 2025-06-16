
# 🗺️ ROADMAP_FRONT – Roadmap de desarrollo del frontend en React

Este roadmap detalla los pasos para construir el frontend en React del caso de estudio de alertas financieras, aplicando buenas prácticas de desarrollo frontend moderno.

---

## ✅ Pasos del frontend

| Paso        | Título                                      | Descripción resumida                                 |
|-------------|---------------------------------------------|------------------------------------------------------|
| FRONT_STEP_1 | Crear proyecto React base                   | Inicializar el proyecto con Vite + TypeScript        |
| FRONT_STEP_2 | Mostrar precio actual de un activo          | Consumir `GET /assets/price?symbol=BTC`              |
| FRONT_STEP_3 | Mostrar historial de precios                | Consumir `/assets/history` y graficar con Recharts   |
| FRONT_STEP_4 | Formulario para registrar alertas           | Crear formulario con `Formik` y validación con `Yup` |
| FRONT_STEP_5 | Visualizar alertas activadas en tiempo real | Conectar a `/alerts/stream` vía Server-Sent Events   |

---

## 📁 Estructura sugerida del proyecto

```
src/
├── components/
├── hooks/
├── pages/
├── services/
├── contexts/
├── App.tsx
└── main.tsx
```

---

## 📌 Tecnologías recomendadas

- **React + Vite + TypeScript**
- **Axios** para HTTP
- **Recharts** para gráficos
- **Formik + Yup** para formularios
- **TailwindCSS o Material UI** para diseño
- **EventSource API** para streaming reactivo
- **Jest + React Testing Library** para pruebas

---

## 🎯 Objetivo final

Obtener una SPA profesional que:
- Consuma el backend reactivo desarrollado previamente
- Visualice datos financieros en tiempo real
- Permita registrar y recibir alertas de precio
- Demuestre todos los conceptos clave de React moderno

---
