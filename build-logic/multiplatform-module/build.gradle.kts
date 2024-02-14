plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform("dev.programadorthi.platform:plugins-platform"))
    implementation(kotlin("gradle-plugin"))
    implementation("com.android.tools.build:gradle")
    implementation(project(":compose-module"))
}