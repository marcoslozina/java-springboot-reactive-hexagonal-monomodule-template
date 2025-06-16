# 🔐 Paso 9 – Seguridad con JWT / Keycloak

## Objetivo

Proteger el backend usando JWT o integración con Keycloak para autenticación y autorización.

---

## Subtareas

1. Agregar dependencia `spring-boot-starter-oauth2-resource-server`
2. Configurar `SecurityWebFilterChain` en clase `SecurityConfig`
3. Validar JWT firmado desde Keycloak o Auth0
4. Agregar roles (scopes) y proteger endpoints sensibles (`/alerts`, `/stream`)
5. Leer claims desde el `Principal` en endpoints
6. Configurar `spring.security.oauth2.resourceserver.jwt.*` en `application.yml`

---

## Resultado

Solo usuarios autenticados con token válido pueden acceder a endpoints protegidos del backend.