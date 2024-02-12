plugins {
    id("dev.programadorthi.ktor-module")
}

group = "${group}.app"

kotlin {
    jvm {
        mainRun {
            mainClass.set("$group.ApplicationKt")
        }
    }

    sourceSets {
        jvmMain {
            dependencies {
                implementation("dev.programadorthi.full.stack.models:models:$version")
                implementation(libs.bundles.exposed)
                implementation(libs.bundles.ktor.server)
                implementation(libs.h2)
                implementation(libs.logback.classic)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.test.unit.ktor.server)
            }
        }
    }
}


