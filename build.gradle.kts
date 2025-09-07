buildscript {
    dependencies {
        classpath(libs.kotlin.gradlePlugin)
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
}