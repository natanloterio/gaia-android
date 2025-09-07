plugins {
    id("ai.gaiahub.convention.application")
    id("ai.gaiahub.convention.application.compose")
    id("ai.gaiahub.convention.dependencies")
}

android {
    namespace = "ai.gaiahub.app"

    signingConfigs {
        create("release") {
            storeFile = file("keystore/release.keystore")
            storePassword = "your_store_password"
            keyAlias = "your_key_alias"
            keyPassword = "your_key_password"
        }
        create("devDebug") {
            storeFile = file("keystore/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
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
    implementation(libs.androidx.junit.ktx)
}