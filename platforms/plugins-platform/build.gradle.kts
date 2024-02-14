plugins {
    id("java-platform")
}

group = "dev.programadorthi.platform"

dependencies {
    constraints {
        val kotlinVersion = libs.versions.kotlin.get()
        api(kotlin("gradle-plugin", version = kotlinVersion))
        api(kotlin("serialization", version = kotlinVersion))
        api(libs.android.gradle.plugin)
        api(libs.compose.gradle.plugin)
        api(libs.ktor.plugin)
    }
}
