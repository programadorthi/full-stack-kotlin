plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform("dev.programadorthi.platform:plugins-platform"))
    implementation(kotlin("gradle-plugin"))
    implementation("io.ktor.plugin:plugin")
    implementation(project(":commons"))
    implementation(project(":serialization-module"))
}