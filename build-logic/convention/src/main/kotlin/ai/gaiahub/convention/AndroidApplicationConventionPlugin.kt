package ai.gaiahub.convention

import ai.gaiahub.buildconvention.libs.Libs
import com.android.build.api.dsl.ApplicationExtension
import ai.gaiahub.convention.support.AppConfig
import ai.gaiahub.convention.support.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.gms.google-services")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = AppConfig.targetSdk
            }

            dependencies {
                implementationLibs(Libs.Libraries.androidx_junit_ktx)
            }
        }
    }
}
