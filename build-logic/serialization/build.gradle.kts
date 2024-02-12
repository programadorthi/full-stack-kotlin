plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(kotlin("gradle-plugin", version = libs.versions.kotlin.get()))
    implementation(kotlin("serialization", version = libs.versions.kotlin.get()))
    implementation(project(":commons"))
}