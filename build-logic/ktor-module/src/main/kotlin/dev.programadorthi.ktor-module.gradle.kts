plugins {
    id("dev.programadorthi.serialization")
    id("io.ktor.plugin")
}

group = "$group.server"

kotlin {
    jvm()
}

application {
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}
