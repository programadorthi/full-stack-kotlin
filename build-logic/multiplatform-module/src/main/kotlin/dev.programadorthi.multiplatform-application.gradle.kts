plugins {
    id("com.android.application")
    id("dev.programadorthi.compose-module")
}

val catalog = versionCatalogs.named("libs")

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "${project.name}Shared"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(catalog.findLibrary("compose-ui-tooling-preview").get())
            implementation(catalog.findLibrary("androidx-activity-compose").get())
        }
    }
}

android {
    val csdk = catalog.findVersion("android-compile-sdk").get().toString().toInt()

    namespace = group.toString()
    compileSdk = csdk

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = catalog.findVersion("android-min-sdk").get().toString().toInt()
        targetSdk = csdk
        versionCode = 1
        versionName = "0.0.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    dependencies {
        debugImplementation(catalog.findLibrary("compose-ui-tooling").get())
    }
}
