plugins {
    id("java-platform")
}

group = "dev.programadorthi.platform"

dependencies {
    constraints {
        api(kotlin("gradle-plugin", version = "1.9.22"))
        api("com.android.tools.build:gradle:8.2.2")
    }
}
