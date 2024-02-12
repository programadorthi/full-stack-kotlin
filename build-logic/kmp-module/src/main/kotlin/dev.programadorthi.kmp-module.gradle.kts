plugins {
    id("dev.programadorthi.serialization")
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()
}
