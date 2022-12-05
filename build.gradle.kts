import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {

    const val HOPLITE_VERSION = "2.7.0"
    const val KOTEST_VERSION = "5.5.4"
    const val MOCKK_VERSION = "1.13.3"
    const val KOIN_VERSION = "3.2.2"
    const val KOIN_ANNOTATIONS_VERSION = "1.0.3"
    const val KOTEST_KOIN_VERSION = "1.1.0"
}

plugins {
    kotlin("jvm") version "1.7.20"
    id("com.google.devtools.ksp") version "1.7.20-1.0.7"
    id("com.apollographql.apollo3") version "3.7.1"
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
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.1")
    implementation("io.insert-koin:koin-core:${Versions.KOIN_VERSION}")
    implementation("io.insert-koin:koin-annotations:${Versions.KOIN_ANNOTATIONS_VERSION}")

    ksp("io.insert-koin:koin-ksp-compiler:${Versions.KOIN_ANNOTATIONS_VERSION}")

    testImplementation("io.kotest:kotest-runner-junit5:${Versions.KOTEST_VERSION}")
    testImplementation("io.insert-koin:koin-test:${Versions.KOIN_VERSION}")
    testImplementation("io.insert-koin:koin-test-junit5:${Versions.KOIN_VERSION}")
    testImplementation("io.kotest.extensions:kotest-extensions-koin:${Versions.KOTEST_KOIN_VERSION}")
    testImplementation("io.mockk:mockk:${Versions.MOCKK_VERSION}")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}

apollo {
    packageName.set("xyz.atrius.graphql")
    excludes.add("src/main/graphql/schema.graphql")
    generateKotlinModels.set(true)
}