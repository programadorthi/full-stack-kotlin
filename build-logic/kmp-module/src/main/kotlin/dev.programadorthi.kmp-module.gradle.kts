plugins {
    id("dev.programadorthi.serialization-module")
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()
}
