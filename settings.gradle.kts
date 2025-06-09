pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://repo.spring.io/milestone") // ✅ Necesario para spring-cloud
    }
}

rootProject.name = "template-service"

/*
 * Project Structure - Hexagonal Architecture:
 * - domain: Core business logic
 * - application: Use cases, split into commands and queries
 * - adapters: Interfaces for input/output (REST, persistence, messaging)
 * - infrastructure: Technical details (config, security, etc.)
 * - shared: Common utilities
 */

include(
    ":adapters",
    ":adapters:in",
    ":adapters:in:rest",
    ":adapters:out",
    ":adapters:out:messaging",
    ":adapters:out:persistence",
    ":application",
    ":application:commands",
    ":application:queries",
    ":domain",
    ":infrastructure",
    ":shared"
)
