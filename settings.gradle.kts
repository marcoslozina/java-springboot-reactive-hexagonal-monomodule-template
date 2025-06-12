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

rootProject.name = "template-service-monomodule"
