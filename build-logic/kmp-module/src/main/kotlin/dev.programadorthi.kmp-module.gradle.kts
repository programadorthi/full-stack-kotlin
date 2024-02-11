plugins {
    id("dev.programadorthi.commons")
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()
}
