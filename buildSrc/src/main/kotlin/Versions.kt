/**
 * 🎯 Centralized version declarations for all project dependencies and plugins.
 * This improves maintainability and makes it easy to upgrade versions in one place.
 */
object Versions {

    // ───────────────────────────────────────────────────────────────
    // 🔧 Gradle Plugins & Toolchain
    // ───────────────────────────────────────────────────────────────
    const val springBoot = "3.2.5"
    const val dependencyManagement = "1.1.7"
    const val kotlin = "1.9.24"
    const val kotlinSpring = "2.1.21"
    const val jacoco = "0.8.10"
    const val checkstyleVersion = "10.12.2"

    // ───────────────────────────────────────────────────────────────
    // 📈 Observability & Monitoring
    // ───────────────────────────────────────────────────────────────
    const val micrometer = "1.12.3" // Micrometer metrics for Prometheus

    // ───────────────────────────────────────────────────────────────
    // 📘 API Documentation (OpenAPI / Swagger)
    // ───────────────────────────────────────────────────────────────
    const val springdocOpenApi = "2.5.0"

    // ───────────────────────────────────────────────────────────────
    // ✅ Validation Frameworks
    // ───────────────────────────────────────────────────────────────
    const val jakartaValidation = "3.0.2"
    const val hibernateValidator = "8.0.1.Final"
    const val jakartaEl = "4.0.2"

    // ───────────────────────────────────────────────────────────────
    // 🧪 Testing & QA
    // ───────────────────────────────────────────────────────────────
    const val junit = "5.10.1"
    const val junitPlatform = "1.10.1"
    const val wiremock = "3.0.1"
    const val restAssured = "5.3.1"
    const val archunit = "1.2.1"

    // ───────────────────────────────────────────────────────────────
    // 📝 Logging
    // ───────────────────────────────────────────────────────────────
    const val logstashLogback = "7.4"
    const val logback = "1.4.14" // Última versión estable con fixes de seguridad

    // ───────────────────────────────────────────────────────────────
    // 🔐 Security-Related Libraries (CVE Mitigation)
    // ───────────────────────────────────────────────────────────────
    const val commonsBeanutils = "1.9.4"
    const val commonsIo = "2.15.1"
    const val httpClient5 = "5.3.1"
    const val artemis = "2.33.0"
    const val jetty = "11.0.21"
    const val xmlunit = "2.9.1"
    const val bcprov = "1.78.1"
    const val nimbusJoseJwt = "9.38.4"
    const val netty = "4.1.111.Final"

    // ───────────────────────────────────────────────────────────────
    // 🧱 Spring Core (para referencias específicas fuera del BOM)
    // ───────────────────────────────────────────────────────────────
    const val spring = "6.1.8"
    const val springSecurity = "6.2.4"
}
