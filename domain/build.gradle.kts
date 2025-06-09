plugins {
    jacoco
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.named("jacocoTestReport"))
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

val junitVersion: String by project

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
