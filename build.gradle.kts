import Versions
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("jacoco")
    id("org.springframework.boot") version Versions.springBoot
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "2.1.21"
    id("checkstyle")
}

group = "com.company.project"
version = "1.0.0-RELEASE"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}")
    }
}

dependencies {
    // ✅ Usar dependencias centralizadas
    implementation(Dependencies.Spring.bootWebflux)
    implementation(Dependencies.Spring.bootSecurity)
    implementation(Dependencies.Spring.bootOauth2)

    // Observabilidad, OpenAPI, Validaciones
    implementation(Dependencies.Observability.micrometerPrometheus)
    implementation(Dependencies.OpenAPI.springdocWebflux)
    implementation(Dependencies.Validation.jakartaValidation)
    implementation(Dependencies.Validation.hibernateValidator)
    implementation(Dependencies.Validation.jakartaEl)

    // Logging y seguridad
    implementation(Dependencies.Logging.logstashLogback)

    // Test
    testImplementation(Dependencies.Spring.bootTest)
    testImplementation(Dependencies.Test.junitApi)
    testImplementation(Dependencies.Test.junitEngine)
    testImplementation(Dependencies.Test.archunit)
    testImplementation(Dependencies.Test.reactorTest)
    testImplementation(Dependencies.Test.springSecurityTest)
}

checkstyle {
    toolVersion = Versions.checkstyleVersion
    configFile = file("src/main/java/com/company/project/templateservice/config/checkstyle/checkstyle.xml")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    classDirectories.setFrom(
        fileTree("build/classes/java/main") {
            exclude("**/config/**", "**/dto/**")
        }
    )
    sourceDirectories.setFrom(files("src/main/java"))
    executionData.setFrom(fileTree("build") {
        include("jacoco/test.exec")
    })
}
