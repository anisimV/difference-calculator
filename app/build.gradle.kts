plugins {
    id("java")
    id("application")
    id("checkstyle")
}

group = "practice.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("info.picocli:picocli:4.7.5")
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("practice.code.App")
}

checkstyle {
    toolVersion = "10.12.3" // Последняя версия на момент 2024 года
    configFile = file("config/checkstyle/checkstyle.xml")
}
