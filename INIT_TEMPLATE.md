
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
  - Cambiar enlaces en badges:
    - ✅ CI: verificar que el archivo del workflow se llame `ci.yml`
      ```markdown
      [![CI](https://github.com/usuario/repositorio/actions/workflows/ci.yml/badge.svg?branch=main)](...)
      ```
    - ✅ Release: usar este badge para mejor compatibilidad
      ```markdown
      [![Last Release](https://img.shields.io/github/release/usuario/repositorio.svg?label=Release)](...)
      ```
    - ✅ Coverage y Vulnerabilities: asegurarse que los `.json` estén publicados en `gh-pages`
      ```markdown
      ![Coverage](https://img.shields.io/endpoint?url=https://usuario.github.io/repositorio/coverage.json)
      ![Vulnerabilities](https://img.shields.io/endpoint?url=https://usuario.github.io/repositorio/security.json)
      ```
    - ✅ SonarCloud: confirmar que el `projectKey` esté correctamente vinculado

- [ ] `sonar-project.properties`
  - Cambiar:
    ```properties
    sonar.projectKey=tu_usuario_tu_repositorio
    sonar.organization=tu_organizacion
    ```
  - Asegurarse de que coincida exactamente con lo definido en el workflow `ci.yml`

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
Pasos:
1. Ir a [https://sonarcloud.io/account/security](https://sonarcloud.io/account/security)
2. Generar y copiar el token
3. Ir al repo → `Settings > Secrets and variables > Actions`
4. Crear nuevo secreto llamado: `SONAR_TOKEN`

> ⚠️ Usar el nombre exacto: `SONAR_TOKEN` (sin sufijos como `_3`)

### `RELEASE_PLEASE_TOKEN`
Token de acceso a GitHub usado por `release-please`  
Pasos:
1. Generar un token clásico en [https://github.com/settings/tokens](https://github.com/settings/tokens)
2. Scopes: `repo` y `workflow`
3. Crear secreto en el repo llamado: `RELEASE_PLEASE_TOKEN`

---

## ⚙️ 4. Ajustar workflows de GitHub Actions

- [ ] `.github/workflows/ci.yml`
  - Verificar que `sonar.projectKey` coincida con `sonar-project.properties`
  - Confirmar publicación de `coverage.json` y `security.json` en la rama `gh-pages`
  - Usar el secreto `SONAR_TOKEN` correctamente nombrado

- [ ] `.github/workflows/release-please.yml`
  - Confirmar que el token `RELEASE_PLEASE_TOKEN` esté configurado
  - Revisar si querés cambiar la convención de versiones

- [ ] `.github/workflows/upload-jar.yml`
  - Verificá que el JAR generado tenga el nombre correcto (`app.jar`, etc.)

---

## 🔧 5. Ajustar Dependabot (opcional)

- [ ] `.github/dependabot.yml`
  - Cambiar reviewers si aplican (ej. `marcoslozina` → tu usuario)
  - Ajustar `ignore:` o `allow:` según tu stack

---

## 📜 6. Otros

- [ ] Borrar este archivo `INIT_TEMPLATE.md` una vez que el setup esté completo ✅
- [ ] Confirmar que `./gradlew build` y `./gradlew sonarqube` funcionen correctamente
- [ ] Confirmar que todos los badges del README se visualicen correctamente

---

## 🌐 7. Crear rama `gh-pages` (si usás GitHub Pages para coverage o badges)

- [ ] Crear manualmente la rama vacía:
  ```bash
  git checkout --orphan gh-pages
  git rm -rf .
  touch .nojekyll
  git add .nojekyll
  git commit -m "init gh-pages"
  git push origin gh-pages
  git checkout main
  ```

- [ ] Activar GitHub Pages:
  - Ir a `Settings > Pages`
  - Elegir fuente: `gh-pages` y carpeta `/ (root)`

- [ ] Confirmar que los archivos `.badge-data/*.json` se publiquen en cada push desde CI
