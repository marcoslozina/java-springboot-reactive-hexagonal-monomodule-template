// build.gradle.kts

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
    id("jacoco")
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    id("checkstyle")
}

group = "com.company.project"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.2.5")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.20")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.13.1")
    testImplementation("com.tngtech.archunit:archunit:1.2.1")
    testImplementation("com.tngtech.archunit:archunit-junit5:1.2.1")
    testImplementation("org.springframework.security:spring-security-test")
}

checkstyle {
    toolVersion = "10.12.2"
    configFile = file("src/main/java/com/company/project/templateservice/config/checkstyle/checkstyle.xml")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

// ✅ Modificamos la tarea existente en lugar de crear una nueva
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
