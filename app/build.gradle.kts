plugins {
    id("ai.gaiahub.convention.application")
    id("ai.gaiahub.convention.application.compose")
    id("ai.gaiahub.convention.dependencies")
}

android {
    namespace = "ai.gaiahub.bara"
}

dependencies {
    implementation(project(":features:login"))
    implementation(project(":core:domain:usecases"))
    implementation(project(":services:tracking"))
    implementation(project(":di"))
    implementation(libs.androidx.junit.ktx)
}

