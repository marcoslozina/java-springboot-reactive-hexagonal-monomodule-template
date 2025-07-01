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
  - Hacia: `com.marcoslozina.investalerts`
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

- [ ] `build.gradle.kts`
  - Ajustar configuración de Checkstyle para reflejar el nuevo package base del proyecto
  ```kotlin
  checkstyle {
      toolVersion = "10.3.1"
      configFile = file("config/checkstyle/checkstyle.xml")
      isIgnoreFailures = false
  }

  tasks.withType<Checkstyle>().configureEach {
      reports {
          xml.required.set(false)
          html.required.set(true)
      }
      source("src/main/java/com/marcoslozina/investalerts")
  }
  ```

---

## 🔐 3. Agregar GitHub Secrets (en el nuevo repositorio)

### `SONAR_TOKEN`
Token necesario para autenticarte con SonarCloud y ejecutar análisis de calidad de código.  
Pasos para obtenerlo:
1. Ir a [https://sonarcloud.io/account/security](https://sonarcloud.io/account/security)
2. En "Generate Tokens", escribir un nombre (ej: `invest-alerts-sonar-token`)
3. Presionar **Generate**
4. Copiar el token y guardarlo
5. Ir al repo en GitHub → `Settings > Secrets and variables > Actions`
6. Crear nuevo secreto: `SONAR_TOKEN`

### `RELEASE_PLEASE_TOKEN`
Token de acceso a GitHub usado por la acción `release-please` para crear versiones y releases.  
Pasos para generarlo:
1. Ir a [https://github.com/settings/tokens](https://github.com/settings/tokens)
2. Elegir "Tokens (classic)" → **Generate new token**
3. Scopes:
   - `repo` ✅
   - `workflow` ✅
4. Crear y copiar el token
5. Ir al repo en GitHub → `Settings > Secrets and variables > Actions`
6. Crear nuevo secreto: `RELEASE_PLEASE_TOKEN`

### Otros (si usás badges externos):
- `GH_TOKEN`: para publicar en GitHub Pages (opcional, usar token clásico con `repo` scope)
- `GIST_ID` y/o `COVERAGE_JSON_URL`: si los badges de cobertura/vulnerabilidades se actualizan desde un gist o endpoint externo

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
---

## 🌐 7. Crear rama `gh-pages` (si usás GitHub Pages para publicar coverage o badges)

- [ ] Crear manualmente la rama `gh-pages` vacía:
  ```bash
  git checkout --orphan gh-pages
  git rm -rf .
  touch .nojekyll
  git add .nojekyll
  git commit -m "init gh-pages"
  git push origin gh-pages
  git checkout main
  ```

- [ ] Asegurarse de que `coverage.json` y `security.json` se publiquen allí automáticamente desde CI

- [ ] Verificar en GitHub → `Settings > Pages` que la fuente esté configurada como:
  - Branch: `gh-pages`
  - Folder: `/root`
