import Versions
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// ─────────────────────────────────────────────────────────────
// 📦 Plugins
// ─────────────────────────────────────────────────────────────
plugins {
    id("jacoco")
    id("checkstyle")
    id("org.springframework.boot") version Versions.springBoot
    id("io.spring.dependency-management") version Versions.dependencyManagement
    kotlin("jvm") version Versions.kotlin
    kotlin("plugin.spring") version Versions.kotlinSpring
}

// ─────────────────────────────────────────────────────────────
// 🏷️ Project metadata
// ─────────────────────────────────────────────────────────────
group = "com.company.project"
version = "1.0.0-RELEASE"

// ─────────────────────────────────────────────────────────────
// ⚙️ Java & Kotlin compatibility
// ─────────────────────────────────────────────────────────────
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

// ─────────────────────────────────────────────────────────────
// 📦 Dependency Management (Spring BOM)
// ─────────────────────────────────────────────────────────────
dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}")
        // Spring Boot 3.5.x still manages JUnit 5.12.x/1.12.x internally; importing the JUnit BOM
        // explicitly keeps jupiter/platform artifacts aligned to Versions.junit (5.14.x line) and
        // avoids classpath split-brain (NoSuchMethodError) between jupiter and platform jars.
        mavenBom("org.junit:junit-bom:${Versions.junit}")
    }
}

// ─────────────────────────────────────────────────────────────
// 📚 Dependencies
// ─────────────────────────────────────────────────────────────
dependencies {
    // 🌐 Core
    implementation(Dependencies.Spring.bootWebflux)
    implementation(Dependencies.Spring.bootSecurity)
    implementation(Dependencies.Spring.bootOauth2)

    // 📊 Observability, 📘 API Docs, ✅ Validation
    implementation(Dependencies.Observability.micrometerPrometheus)
    implementation(Dependencies.OpenAPI.springdocWebflux)
    implementation(Dependencies.Validation.jakartaValidation)
    implementation(Dependencies.Validation.hibernateValidator)
    implementation(Dependencies.Validation.jakartaEl)

    // 📝 Logging
    implementation(Dependencies.Logging.logstashLogback)

    // 🧪 Testing
    testImplementation(Dependencies.Spring.bootTest)
    testImplementation(Dependencies.Test.junitApi)
    testImplementation(Dependencies.Test.junitEngine)
    testImplementation(Dependencies.Test.archunit)
    testImplementation(Dependencies.Test.reactorTest)
    testImplementation(Dependencies.Test.springSecurityTest)
}

// ─────────────────────────────────────────────────────────────
// ✅ Checkstyle Configuration
// ─────────────────────────────────────────────────────────────
checkstyle {
    toolVersion = Versions.checkstyleVersion
    configFile = file("src/main/java/com/company/project/templateservice/config/checkstyle/checkstyle.xml")
}

// ─────────────────────────────────────────────────────────────
// 🧪 Test & Coverage
// ─────────────────────────────────────────────────────────────
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

// ─────────────────────────────────────────────────────────────
// ⚙️ Spring Boot executable JAR
// ─────────────────────────────────────────────────────────────
tasks.named<Jar>("jar") {
    enabled = false // ❌ Desactiva el JAR plano
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = true // ✅ Activa el JAR ejecutable
    archiveFileName.set("app.jar")
}
