plugins {
    id("dev.programadorthi.commons")
    id("io.ktor.plugin")
    kotlin("plugin.serialization")
}

group = "$group.server"

kotlin {
    jvm()
}

application {
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}
