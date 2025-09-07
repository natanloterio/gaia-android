package ai.gaiahub.convention

import ai.gaiahub.buildconvention.libs.Libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate


fun Libs.Library.notation(): String {
    return if (this.version.isNotEmpty()) {
        "${this.group}:${this.name}:${this.version}"
    } else {
        "${this.group}:${this.name}"
    }
}


fun DependencyHandlerDelegate.implementationLibs(lib: Libs.Library) {
    add("implementation", lib.notation())
}

fun DependencyHandlerDelegate.testImplementationLibs(lib: Libs.Library) {
    add("testImplementation", lib.notation())
}

fun DependencyHandlerDelegate.debugImplementationLibs(lib: Libs.Library) {
    add("debugImplementation", lib.notation())
}

fun DependencyHandlerDelegate.androidTestImplementationLibs(lib: Libs.Library) {
    add("androidTestImplementation", lib.notation())
}

fun DependencyHandlerScope.implementationBom(library: Libs.Library) {
    try {
        add("implementation", platform(library.notation()))
    } catch (e: Exception) {
        println("Error applying ${library.notation()}: ${e.message}")
    }
}

/**
 * Returns the value of the specified project property as a String.
 *
 * @param name The name of the property to retrieve.
 * @return The property value if it exists, otherwise an empty string.
 *         Prints a message if the property is not found.
 */
fun Project.prop(name: String): String {
    return if (this.hasProperty(name)) {
        this.property(name) as String
    } else {
        println("Property $name not found in project ${this.name}")
        ""
    }
}

fun Project.applyPropertiesFromFile(filePath: String) {
    val propertiesFile = rootProject.file(filePath)
    if (propertiesFile.exists()) {
        val properties = java.util.Properties()
        properties.load(propertiesFile.inputStream())
        properties.forEach { (key, value) ->
            this.extensions.extraProperties[key.toString()] = value
        }
    }
}
