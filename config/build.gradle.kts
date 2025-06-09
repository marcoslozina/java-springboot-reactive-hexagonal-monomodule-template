plugins {
    jacoco
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.named("jacocoTestReport")) // ✅ Esto es CLAVE
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }

    classDirectories.setFrom(
        fileTree("${buildDir}/classes/java/main")
    )
    sourceDirectories.setFrom(
        files("src/main/java")
    )
    executionData.setFrom(
        fileTree(buildDir).include("jacoco/test.exec")
    )
}
