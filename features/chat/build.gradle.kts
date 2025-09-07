plugins {
    id("ai.gaiahub.convention.library")
    id("ai.gaiahub.convention.library.compose")
    id("ai.gaiahub.convention.dependencies")
}

android {
    namespace = "com.ai.gaiahub.feature.login"
}

dependencies {
    implementation(project(":core:domain:services:tracking"))
    implementation(project(":core:domain:usecases"))
}
