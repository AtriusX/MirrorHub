import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {

    const val KOTEST_VERSION = "5.5.4"
    const val MOCKK_VERSION = "1.13.3"
}


plugins {
    kotlin("jvm") version "1.7.20"
}

group = "xyz.atrius"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.1")

    testImplementation("io.kotest:kotest-runner-junit5:${Versions.KOTEST_VERSION}")
    testImplementation("io.mockk:mockk:${Versions.MOCKK_VERSION}")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}