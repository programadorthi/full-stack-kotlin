plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform("dev.programadorthi.platform:plugins-platform"))
    implementation(kotlin("gradle-plugin"))
    implementation("org.jetbrains.compose:compose-gradle-plugin")
    implementation(project(":commons"))
}