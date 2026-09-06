/**
 * 🎯 Centralized version declarations for all project dependencies and plugins.
 * This improves maintainability and makes it easy to upgrade versions in one place.
 */
object Versions {

    // ───────────────────────────────────────────────────────────────
    // 🔧 Gradle Plugins & Toolchain
    // ───────────────────────────────────────────────────────────────
    const val springBoot = "3.5.16" // ✅ Actualizado de 3.5.3 (última patch de la línea 3.5.x)
    const val dependencyManagement = "1.1.7"
    const val kotlin = "2.2.0" // ✅ Actualizado de 2.1.0
    const val kotlinSpring = "2.2.0" // ✅ Actualizado de 2.1.0
    const val jacoco = "0.8.15" // ✅ Actualizado de 0.8.12
    const val checkstyleVersion = "10.13.0" // ✅ Actualizado de 10.12.2

    // ───────────────────────────────────────────────────────────────
    // 📈 Observability & Monitoring
    // ───────────────────────────────────────────────────────────────
    const val micrometer = "1.17.1" // ✅ Actualizado de 1.15.0 - Micrometer metrics for Prometheus

    // ───────────────────────────────────────────────────────────────
    // 📘 API Documentation (OpenAPI / Swagger)
    // ───────────────────────────────────────────────────────────────
    const val springdocOpenApi = "2.8.6" // ✅ Actualizado de 2.6.0

    // ───────────────────────────────────────────────────────────────
    // ✅ Validation Frameworks
    // ───────────────────────────────────────────────────────────────
    const val jakartaValidation = "3.1.1" // ✅ Actualizado de 3.0.2
    const val hibernateValidator = "9.1.3.Final" // ✅ Actualizado de 9.0.1.Final
    const val jakartaEl = "4.0.2" // Versión estable más reciente (5.0.0-M1 es milestone)

    // ───────────────────────────────────────────────────────────────
    // 🧪 Testing & QA
    // ───────────────────────────────────────────────────────────────
    const val junit = "5.14.4" // ✅ Actualizado de 5.11.2 (última estable de la línea 5.x, antes de JUnit 6)
    const val junitPlatform = "1.14.4" // ✅ Actualizado de 1.11.2 (pareja de junit-jupiter 5.14.4)
    const val archunit = "1.5.0" // ✅ Actualizado de 1.3.0

    // ───────────────────────────────────────────────────────────────
    // 📝 Logging
    // ───────────────────────────────────────────────────────────────
    const val logstashLogback = "8.1" // ✅ Actualizado de 8.0
    const val logback = "1.5.18" // ✅ Actualizado de 1.5.14 - Última versión estable con fixes de seguridad

    // ───────────────────────────────────────────────────────────────
    // 🔐 Security-Related Libraries (CVE Mitigation)
    // ───────────────────────────────────────────────────────────────
    const val commonsBeanutils = "1.9.4"
    const val commonsIo = "2.16.1" // ✅ Actualizado de 2.15.1 - Corrige vulnerabilidades
    const val httpClient5 = "5.3.2" // ✅ Actualizado de 5.3.1
    const val artemis = "2.34.0" // ✅ Actualizado de 2.33.0
    const val jetty = "11.0.24" // ✅ Actualizado de 11.0.21 - Compatible con Spring Boot 3.5.3 y corrige vulnerabilidades
    const val xmlunit = "2.10.0" // ✅ Actualizado de 2.9.1
    const val bcprov = "1.78.1"
    const val nimbusJoseJwt = "9.45.4" // ✅ Actualizado de 9.38.4 - Corrige vulnerabilidades
    const val netty = "4.1.116.Final" // ✅ Actualizado de 4.1.111.Final - Corrige vulnerabilidades

    // ───────────────────────────────────────────────────────────────
    // 🧱 Spring Core (para referencias específicas fuera del BOM)
    // ───────────────────────────────────────────────────────────────
    const val spring = "6.2.6" // ✅ Actualizado de 6.1.18 - Compatible con Spring Boot 3.5.3
    const val springSecurity = "6.5.1" // ✅ Actualizado de 6.3.4 - Compatible con Spring Boot 3.5.3
}
