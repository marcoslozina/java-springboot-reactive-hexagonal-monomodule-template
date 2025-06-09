plugins {
    jacoco
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport") // Terminar tests generando reporte
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }

    classDirectories.setFrom(
        fileTree("${buildDir}/classes/java/main") // Clases compiladas
    )
    sourceDirectories.setFrom(
        files("src/main/java") // Código fuente
    )
    executionData.setFrom(
        fileTree(buildDir).include("jacoco/test.exec") // Archivo de cobertura
    )
}
