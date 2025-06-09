/**
 * Centralized version declarations for all project dependencies.
 * This improves maintainability and makes it easy to upgrade versions in one place.
 */
object Versions {

    // 📈 Observability & Monitoring
    const val micrometer = "1.12.3" // Micrometer metrics library (used with Prometheus)

    // 📘 API Documentation
    const val springdocOpenApi = "2.5.0" // OpenAPI + Swagger support for Spring WebFlux

    // ✅ Validation Framework
    const val jakartaValidation = "3.0.2" // Jakarta Bean Validation API
    const val hibernateValidator = "8.0.1.Final" // Hibernate Validator implementation
    const val jakartaEl = "4.0.2" // Jakarta Expression Language (required by Hibernate Validator)

    // 🧪 Testing
    const val junit = "5.10.1"
    const val junitPlatform = "1.10.1"
    const val wiremock = "3.0.1"
    const val restAssured = "5.3.1"
    const val archunit = "1.2.1"

    // 📝 Logging
    const val logstashLogback = "7.4"
    const val logback = "1.4.14" // 🔐 updated to fix known vulnerabilities

    // 🔐 Security-related fixes (updated for CVE mitigation)
    const val commonsBeanutils = "1.9.4"
    const val commonsIo = "2.15.1" // 🔐 updated from 2.15.0
    const val httpClient5 = "5.3.1" // 🔐 updated from 5.2.1
    const val artemis = "2.33.0" // 🔐 updated from 2.31.2
    const val jetty = "11.0.21" // 🔐 updated from 11.0.20
    const val xmlunit = "2.9.1"
    const val bcprov = "1.78.1"
    const val nimbusJoseJwt = "9.38.4" // 🔐 updated from 9.37.3
    const val netty = "4.1.111.Final" // 🔐 updated from 4.1.110.Final
    const val spring = "6.1.8" // 🔐 updated from 6.1.6
    const val springSecurity = "6.2.4" // 🔐 updated from 6.2.3
    const val versionCommonsIo = "2.15.1"
    const val versionJetty = "11.0.20"
}
