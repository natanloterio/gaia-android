buildscript {
    dependencies {
        classpath(libs.kotlin.gradlePlugin)
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.ktlint.android) apply true
    alias(libs.plugins.google.services) apply true
}