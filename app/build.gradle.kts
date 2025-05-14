plugins {
    id("java")
    id("application")
}

group = "practice.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("info.picocli:picocli:4.7.5") // последняя стабильная версия
    annotationProcessor("info.picocli:picocli-codegen:4.7.5") // для генерации
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("practice.code.App")
}
