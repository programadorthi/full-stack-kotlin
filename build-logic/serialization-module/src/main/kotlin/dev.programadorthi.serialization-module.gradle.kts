import kotlin.jvm.optionals.getOrNull

plugins {
    id("dev.programadorthi.commons")
    kotlin("plugin.serialization")
}

val versionCatalogs: VersionCatalogsExtension by extensions

kotlin {
    dependencies {
        for (catalogName in versionCatalogs.catalogNames) {
            val libs = versionCatalogs.find(catalogName).getOrNull()
            if (libs != null) {
                commonMainApi(libs.findLibrary("serialization-core").get())
            }
        }
    }
}
