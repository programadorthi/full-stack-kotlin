plugins {
    id("java-platform")
}

group = "dev.programadorthi.platform"

val kotlin_version: String by project

dependencies {
    constraints {
        api(kotlin("gradle-plugin", version = kotlin_version))
        api(kotlin("plugin.serialization", version = kotlin_version))
        api("com.android.tools.build:gradle:8.2.2")
        api("io.ktor.plugin:plugin::2.3.8")
    }
}
