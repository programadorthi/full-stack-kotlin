import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    id("dev.programadorthi.serialization-module")
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = project.name
        browser {
            commonWebpackConfig {
                outputFileName = "${project.name}.js"
            }
        }
        binaries.executable()
    }
}
