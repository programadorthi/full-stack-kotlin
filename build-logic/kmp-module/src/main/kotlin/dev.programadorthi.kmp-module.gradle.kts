import org.gradle.kotlin.dsl.kotlin

plugins {
    kotlin("multiplatform")
}

kotlin {
    explicitApi()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()
}