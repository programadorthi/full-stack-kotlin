plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(kotlin("gradle-plugin", version = libs.versions.kotlin.get()))
    implementation(libs.ktor.plugin)
    implementation(project(":commons"))
    implementation(project(":serialization"))
}