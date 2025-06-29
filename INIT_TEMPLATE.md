# 🚀 INIT_TEMPLATE.md – Checklist de Inicialización de Proyecto

Este archivo te guía paso a paso para personalizar correctamente un nuevo proyecto clonado desde este template base (`java-springboot-reactive-hexagonal-monomodule-template`).

---

## 🧾 1. Renombrar el proyecto

- [ ] Renombrar en `settings.gradle.kts`:
  ```kotlin
  rootProject.name = "invest-alerts-reactive-backend"
  ```

- [ ] Cambiar el paquete base si aplica:
  - Desde: `com.company.project`
  - Hacia: `com.miempresa.miapp`
  - Usar "Buscar y Reemplazar" en el IDE

---

## 🛠️ 2. Actualizar archivos clave

- [ ] `README.md`
  - Reemplazar nombre del proyecto y descripción
  - Cambiar enlaces en badges (CI, cobertura, release, SonarCloud)
  - Actualizar imágenes si son propias del template (cambiar rutas)

- [ ] `sonar-project.properties`
  - Cambiar `sonar.projectKey` y `sonar.organization`
  - Asegurarse de tener el token `SONAR_TOKEN` configurado en GitHub

- [ ] `.env`
  - Renombrar base de datos (`DB_NAME=invest_alerts_db`)
  - Cambiar credenciales si será usado en producción

- [ ] `docker-compose.yml`
  - Renombrar servicios (por ejemplo, `template-service` → `invest-alerts-service`)
  - Cambiar puertos si necesitás ejecutar varios servicios

- [ ] `gradle.properties` (opcional)
  - No suele requerir cambios salvo que ajustes `javaVersion`

---

## 🔐 3. Agregar GitHub Secrets (en el nuevo repositorio)

- [ ] `SONAR_TOKEN`
- [ ] `GH_TOKEN` (para publicar a GitHub Pages)
- [ ] `RELEASE_PLEASE_TOKEN` (si usás Release Please)
- [ ] `GIST_ID` y/o `COVERAGE_JSON_URL` si usás badges externos

---

## ⚙️ 4. Ajustar workflows de GitHub Actions

- [ ] `.github/workflows/ci.yml`
  - Cambiar `sonar.projectKey` y `organization`
  - Revisar que `coverage.json` y `security.json` se publiquen en la rama correcta

- [ ] `.github/workflows/release-please.yml`
  - Confirmar que el token esté disponible como secret
  - Cambiar patrón de nombre si lo deseás

- [ ] `.github/workflows/upload-jar.yml`
  - Verificá que el JAR generado tenga el nombre correcto

---

## 🔧 5. Ajustar Dependabot (opcional)

- [ ] `.github/dependabot.yml`
  - Cambiar reviewers si aplican (ej. `marcoslozina` → tu usuario)
  - Ajustar `ignore:` o `allow:` según tu stack

---

## 📜 6. Otros

- [ ] Borrar este archivo `INIT_TEMPLATE.md` una vez que el setup esté completo ✅
- [ ] Verificar que `./gradlew build` y `./gradlew sonarqube` funcionen correctamente
- [ ] Confirmar que los badges del README se actualicen en `gh-pages`
