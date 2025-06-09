import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

object Dependencies {

    object Spring {
        const val bootWebflux = "org.springframework.boot:spring-boot-starter-webflux"
        const val bootSecurity = "org.springframework.boot:spring-boot-starter-security"
        const val bootOauth2 = "org.springframework.boot:spring-boot-starter-oauth2-resource-server"
        const val bootTest = "org.springframework.boot:spring-boot-starter-test"
        const val securityConfig = "org.springframework.security:spring-security-config"
    }

    object Cloud {
        const val vaultConfig = "org.springframework.cloud:spring-cloud-starter-vault-config"
    }

    object Observability {
        val micrometerPrometheus = "io.micrometer:micrometer-registry-prometheus:${Versions.micrometer}"
    }

    object Logging {
        val logstashLogback = "net.logstash.logback:logstash-logback-encoder:${Versions.logstashLogback}"
    }

    object OpenAPI {
        val springdocWebflux = "org.springdoc:springdoc-openapi-starter-webflux-ui:${Versions.springdocOpenApi}"
    }

    object Validation {
        val jakartaValidation = "jakarta.validation:jakarta.validation-api:${Versions.jakartaValidation}"
        val hibernateValidator = "org.hibernate.validator:hibernate-validator:${Versions.hibernateValidator}"
        val jakartaEl = "org.glassfish:jakarta.el:${Versions.jakartaEl}"
    }

    object Test {
        val junitApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
        val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
        val wiremock = "com.github.tomakehurst:wiremock-jre8:${Versions.wiremock}"
        val restAssured = "io.rest-assured:rest-assured:${Versions.restAssured}"
        const val reactorTest = "io.projectreactor:reactor-test"
        const val springSecurityTest = "org.springframework.security:spring-security-test"
        val archunit = "com.tngtech.archunit:archunit-junit5:${Versions.archunit}"

        val junitPlatformCommonsStrict = moduleWithStrictVersion(
            "org.junit.platform:junit-platform-commons", Versions.junitPlatform
        )
    }
}

object SecurityFixes {
    val commonsBeanutils = "commons-beanutils:commons-beanutils:${Versions.commonsBeanutils}"
    val commonsIo = "commons-io:commons-io:${Versions.commonsIo}"
    val httpClient5 = "org.apache.httpcomponents.client5:httpclient5:${Versions.httpClient5}"

    val activemqArtemis = "org.apache.activemq:artemis-core-client:${Versions.artemis}"

    val jettyServer = "org.eclipse.jetty:jetty-server:${Versions.jetty}"
    val jettyHttp = "org.eclipse.jetty:jetty-http:${Versions.jetty}"
    val jettyUtil = "org.eclipse.jetty:jetty-util:${Versions.jetty}"

    val logbackCore = "ch.qos.logback:logback-core:${Versions.logback}"
    val logbackClassic = "ch.qos.logback:logback-classic:${Versions.logback}"

    val xmlunit = "org.xmlunit:xmlunit-core:${Versions.xmlunit}"

    val bcprov = "org.bouncycastle:bcprov-jdk18on:${Versions.bcprov}"

    val nimbusJoseJwt = "com.nimbusds:nimbus-jose-jwt:${Versions.nimbusJoseJwt}"

    val nettyCommon = "io.netty:netty-common:${Versions.netty}"
    val nettyHandler = "io.netty:netty-handler:${Versions.netty}"

    val springWeb = "org.springframework:spring-web:${Versions.spring}"
    val springContext = "org.springframework:spring-context:${Versions.spring}"
    val springSecurityWeb = "org.springframework.security:spring-security-web:${Versions.springSecurity}"
    val springSecurityCore = "org.springframework.security:spring-security-core:${Versions.springSecurity}"
    val springSecurityCrypto = "org.springframework.security:spring-security-crypto:${Versions.springSecurity}"
}

/**
 * Helper function to strictly enforce dependency versions.
 */
fun moduleWithStrictVersion(groupAndName: String, version: String): ExternalModuleDependency =
    DefaultExternalModuleDependency(
        groupAndName.substringBefore(":"), groupAndName.substringAfter(":"), version
    ).apply {
        this.version {
            strictly(version)
        }
    }
