![Build Status](https://gitlab.com/tu-grupo/tu-proyecto/badges/main/pipeline.svg)
![Coverage](https://gitlab.com/tu-grupo/tu-proyecto/badges/main/coverage.svg)
🚀 Hexagonal Architecture Template

Overview

This project serves as a template for implementing Hexagonal Architecture in a Java-based application using Spring Boot.
It promotes modularization, separation of concerns, and better testability by isolating the domain layer from external
dependencies.

📌 Technologies Used

Java: 21 (Amazon Corretto)

Spring Boot: 3.2.4

Gradle: 8.10

R2DBC: Reactive database connectivity

ArchUnit: Architecture validation

JUnit 5: Unit testing

Micrometer: Metrics & monitoring

OpenAPI: API documentation

📦 Project Structure

This project follows a Hexagonal Architecture pattern with the following modules:

* ├── adapters/ # External communication (APIs, DB, etc.)
* ├── application/ # Use cases and application services
* ├── domain/ # Core business logic and entities
* ├── config/ # Configuration files (Beans, Security, etc.)
* ├── shared/ # Shared utilities and common logic
* ├── docs/ # Documentation (OpenAPI, Architecture diagrams, etc.)
* └── mutation-tests/ # Mutation testing setup`

🛠️ Build and Run the Project

1️⃣ Compile the project

./gradlew clean build

2️⃣ Run the application

./gradlew bootRun

3️⃣ Run tests

./gradlew test

🔍 Validate Hexagonal Architecture

To ensure the project follows Hexagonal Architecture principles, run the following test:

./gradlew test --tests com.company.templateservice.architecture.ArchitectureTest

📌 If the test fails, it indicates architecture violations.

📌 Environment Configuration

Set up Java 21 using SDKMAN

sdk install java 21.0.5-amzn
sdk default java 21.0.5-amzn

Ensure Gradle uses Java 21

Add this to gradle.properties:

org.gradle.java.home=/home/user/.sdkman/candidates/java/current

📌 Additional Resources

Spring Boot Documentation

Hexagonal Architecture Explained

SDKMAN!

📌 This template ensures a clean and scalable architecture for your applications! 🚀🔥

🚀 Resumen de Diferencias
Entorno Quién lo usa Propósito Tipo de BD Logs
Dev (application-dev.yml)    Desarrolladores Pruebas unitarias y debugging BD en memoria o desarrollo DEBUG
QC (application-qc.yml)    QA (Quality Assurance)    Pruebas funcionales y automatizadas BD de pruebas real INFO
UAT (application-uat.yml)    Usuarios finales Validación antes de producción BD similar a producción WARN
Staging (application-staging.yml)    Infraestructura y operaciones Pruebas de carga y performance BD idéntica a
producción ERROR
📌 Conclusión:

## 🚀 Instalación de Hooks de Git

Después de clonar el repositorio, ejecutar:

```bash
bash git-hooks/install-hooks.sh


Dev → Para desarrolladores.
QC → Para pruebas de calidad.
UAT → Para pruebas de aceptación con usuarios reales.
Staging → Última validación antes de producción.

