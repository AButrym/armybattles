plugins {
    kotlin("jvm") version "2.1.21"
}

group = "softserve.academy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    
    // SLF4J and Logback dependencies
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.11")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}