buildscript {
    dependencies {
        classpath(libs.kotlin.gradlePlugin)
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.