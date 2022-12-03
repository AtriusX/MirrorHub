import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {

    const val HOPLITE_VERSION = "2.7.0"
    const val KOTEST_VERSION = "5.5.4"
    const val MOCKK_VERSION = "1.13.3"
}


plugins {
    kotlin("jvm") version "1.7.20"
}

group = "xyz.atrius"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.sksamuel.hoplite:hoplite-core:${Versions.HOPLITE_VERSION}")
    implementation("com.sksamuel.hoplite:hoplite-yaml:${Versions.HOPLITE_VERSION}")
    implementation("com.sksamuel.hoplite:hoplite-watch:${Versions.HOPLITE_VERSION}")

    testImplementation("io.kotest:kotest-runner-junit5:${Versions.KOTEST_VERSION}")
    testImplementation("io.mockk:mockk:${Versions.MOCKK_VERSION}")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}