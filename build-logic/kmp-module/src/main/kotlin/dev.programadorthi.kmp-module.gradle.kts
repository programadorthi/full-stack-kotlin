import org.gradle.kotlin.dsl.kotlin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
}

group = "dev.programadorthi.full.stack"

kotlin {
    explicitApi()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}