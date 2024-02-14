plugins {
    id("dev.programadorthi.commons")
    id("org.jetbrains.compose")
}

kotlin {
    dependencies {
        commonMainImplementation(compose.runtime)
    }
}
