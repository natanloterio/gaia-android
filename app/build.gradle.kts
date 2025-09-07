import ai.gaiahub.convention.applyPropertiesFromFile
import ai.gaiahub.convention.prop

plugins {
    id("ai.gaiahub.convention.application")
    id("ai.gaiahub.convention.application.compose")
    id("ai.gaiahub.convention.dependencies")
}

applyPropertiesFromFile("keystore/debug.keystore.properties")
applyPropertiesFromFile("keystore/release.keystore.properties")

android {
    namespace = "ai.gaiahub.app"
    defaultConfig {
        applicationId = "ai.gaiahub.app"
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("${rootDir}/keystore/release.keystore.jks")
            keyPassword = prop("releaseKeyPassword")
            keyAlias = prop("releaseKeyAlias")
            storePassword = prop("releaseStorePassword")
        }
        create("devDebug") {
            storeFile = file("${rootDir}/keystore/debug.keystore.jks")
            keyPassword = prop("debugStorePassword")
            keyAlias = prop("debugKeyAlias")
            storePassword = prop("debugStorePassword")
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("devDebug")
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
        }
    }
}
dependencies {
    implementation(project(":features:login"))
    implementation(project(":core:domain:usecases"))
    implementation(project(":services:tracking"))
    implementation(project(":di"))
}