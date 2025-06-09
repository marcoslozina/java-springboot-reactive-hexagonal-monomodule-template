plugins {
    java
    jacoco
    id("checkstyle")
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.diffplug.spotless") version "7.0.4"
    id("org.sonarqube") version "6.2.0.5505"
    id("org.owasp.dependencycheck") version "12.1.1"
    id("com.github.ben-manes.versions") version "0.52.0"
}

val logstashLogbackVersion = project.findProperty("logstashLogbackVersion") as String
val springBootVersion = project.findProperty("springBootVersion") as String
val springCloudVersion = project.findProperty("springCloudVersion") as String
val dependencyManagementVersion = project.findProperty("dependencyManagementVersion") as String
val checkstyleVersion = project.findProperty("checkstyleVersion") as String
val javaVersion = project.findProperty("javaVersion") as String
val junitVersion = project.findProperty("junitVersion") as String
val junitPlatformVersion = project.findProperty("junitPlatformVersion") as String

group = "com.company.templateservice"
version = "1.0.0-RELEASE"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion.toInt()))
    }
}

springBoot {
    mainClass.set("com.company.project.templateservice.Application")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        mavenBom("org.junit:junit-bom:$junitVersion")
    }
}

dependencies {
    implementation(Dependencies.Observability.micrometerPrometheus)
    implementation(Dependencies.Cloud.vaultConfig)
    implementation(Dependencies.Spring.bootOauth2)
    implementation(Dependencies.Spring.bootSecurity)
    implementation(Dependencies.Spring.bootWebflux)
    implementation(Dependencies.Spring.securityConfig)
    implementation(Dependencies.OpenAPI.springdocWebflux)
    implementation(Dependencies.Validation.jakartaValidation)
    implementation(Dependencies.Validation.hibernateValidator)
    implementation(Dependencies.Validation.jakartaEl)
    implementation(Dependencies.Logging.logstashLogback)

    // 🔐 Seguridad: Corrección de vulnerabilidades detectadas
    implementation(SecurityFixes.commonsBeanutils)
    implementation(SecurityFixes.commonsIo)
    implementation(SecurityFixes.httpClient5)
    implementation(SecurityFixes.activemqArtemis)
    implementation(SecurityFixes.jettyServer)
    implementation(SecurityFixes.jettyHttp)
    implementation(SecurityFixes.commonsBeanutils)
    implementation(SecurityFixes.commonsIo)
    implementation(SecurityFixes.httpClient5)
    implementation(SecurityFixes.activemqArtemis)
    implementation(SecurityFixes.jettyServer)
    implementation(SecurityFixes.jettyHttp)

    // ✅ Fijar versión estricta de junit-platform-commons para evitar conflictos
    add("implementation", Dependencies.Test.junitPlatformCommonsStrict)

    testImplementation(Dependencies.Test.wiremock)
    testImplementation(Dependencies.Test.restAssured)
    testImplementation(Dependencies.Test.junitApi)
    testImplementation(Dependencies.Spring.bootTest) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "org.junit.jupiter", module = "junit-jupiter")
        exclude(group = "org.junit", module = "junit-bom")
        exclude(group = "org.junit.platform", module = "junit-platform-commons")
    }
    testImplementation(Dependencies.Test.reactorTest)
    testImplementation(Dependencies.Test.springSecurityTest)
    testImplementation(Dependencies.Test.archunit)
    testRuntimeOnly(Dependencies.Test.junitEngine)

    testImplementation(project(":adapters:in:rest"))
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.named<Jar>("bootJar") {
    archiveFileName.set("app.jar")
}

tasks.register("audit") {
    group = "verification"
    description = "Run full project checks including code style, tests, and security audits"
    dependsOn("checkAll")
}

tasks.register<Test>("archTest") {
    group = "verification"
    description = "Runs architecture tests"
    useJUnitPlatform()
    include("**/*ArchitectureTest.class")
}

spotless {
    java {
        googleJavaFormat("1.17.0")
        target("src/**/*.java")
    }
}

checkstyle {
    toolVersion = checkstyleVersion
    configFile = file("$rootDir/config/checkstyle/checkstyle.xml")
    isIgnoreFailures = false
}

sonarqube {
    properties {
        property("sonar.projectKey", "marcoslozina_template-service")
        property("sonar.organization", "marcoslozina")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.token", System.getenv("SONAR_TOKEN"))
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/jacocoRootReport/jacocoRootReport.xml")
        property("sonar.coverage.exclusions", "**/Application.java, **/DummyService.java, **/AdminController.java, **/config/**, **/integration/**, **/architecture/**, **/logging/**, **/security/**")
        property("sonar.newCode.referenceBranch", "main")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    group = rootProject.group
    version = rootProject.version

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion.toInt()))
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

    tasks.register("printVersion") {
        group = "info"
        description = "Prints the project version"
        doLast {
            println("Project version: \${project.version}")
        }
    }
}

tasks.register<JacocoReport>("jacocoRootReport") {
    group = "verification"
    description = "Generates a unified code coverage report from all subprojects."

    mustRunAfter("spotlessJava")
    dependsOn("spotlessApply")
    dependsOn(
        tasks.named("test"),
        tasks.named("jacocoTestReport"),
        subprojects.mapNotNull { it.tasks.findByName("test") },
        subprojects.mapNotNull { it.tasks.findByName("jacocoTestReport") }
    )

    executionData.setFrom(
        fileTree(rootDir).include("**/build/jacoco/test.exec")
    )

    classDirectories.setFrom(
        files(
            subprojects.map {
                fileTree("${it.buildDir}/classes/java/main") {
                    exclude("**/infrastructure/**", "**/shared/**", "**/config/**")
                }
            }
        )
    )

    sourceDirectories.setFrom(
        files(subprojects.map { it.projectDir.resolve("src/main/java") })
    )

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}

configurations.all {
    resolutionStrategy {
        force("org.junit.jupiter:junit-jupiter:$junitVersion")
        force("org.junit.jupiter:junit-jupiter-api:$junitVersion")
        force("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
        force("org.junit.platform:junit-platform-commons:$junitPlatformVersion")
        force("org.junit.platform:junit-platform-engine:$junitPlatformVersion")
        force("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
    }
}
