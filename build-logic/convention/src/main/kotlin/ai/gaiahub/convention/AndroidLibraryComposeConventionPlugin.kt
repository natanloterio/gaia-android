package ai.gaiahub.convention

import ai.gaiahub.convention.support.configureAndroidCompose
import ai.gaiahub.convention.support.libraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            configureAndroidCompose(libraryExtension)
        }
    }
}
