import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.jvm.optionals.getOrNull

plugins {
    kotlin("multiplatform")
}

group = "dev.programadorthi.full.stack"

val versionCatalogs: VersionCatalogsExtension by extensions

kotlin {
    explicitApi()

    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }

    dependencies {
        for (catalogName in versionCatalogs.catalogNames) {
            val libs = versionCatalogs.find(catalogName).getOrNull()
            if (libs != null) {
                commonMainApi(libs.findLibrary("coroutines-core").get())
            }
        }
        commonTestImplementation(kotlin("test"))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

tasks.withType<AbstractTestTask> {
    testLogging {
        events("PASSED", "FAILED", "SKIPPED")
        exceptionFormat = TestExceptionFormat.FULL
        showStandardStreams = true
        showStackTraces = true
    }
}
