package ai.gaiahub.convention

import org.gradle.api.Project
import org.gradle.internal.extensions.core.extra


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
            project.extra[key.toString()] = value
        }
    }
}


//
//import ai.gaiahub.buildconvention.libs.Libs
//import org.gradle.kotlin.dsl.DependencyHandlerScope
//import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate
//import kotlin.text.isNotEmpty
//
//fun Libs.Library.notation(): String {
//    return if (this.version.isNotEmpty()) {
//        "${this.group}:${this.name}:${this.version}"
//    } else {
//        "${this.group}:${this.name}"
//    }
//}
//
//fun DependencyHandlerDelegate.implementationLibs(lib: Libs.Library) {
//    DependencyHandlerDelegate.add("implementation", lib.notation())
//}
//
//fun DependencyHandlerDelegate.testImplementationLibs(lib: Libs.Library) {
//    DependencyHandlerDelegate.add("testImplementation", lib.notation())
//}
//
//fun DependencyHandlerDelegate.debugImplementationLibs(lib: Libs.Library) {
//    DependencyHandlerDelegate.add("debugImplementation", lib.notation())
//}
//
//fun DependencyHandlerDelegate.androidTestImplementationLibs(lib: Libs.Library) {
//    DependencyHandlerDelegate.add("androidTestImplementation", lib.notation())
//}
//
//fun DependencyHandlerScope.implementationBom(library: Libs.Library) {
//    try {
//        DependencyHandlerDelegate.add(
//            "implementation",
//            DependencyHandlerDelegate.platform(library.notation())
//        )
//    } catch (e: Exception) {
//        println("Error applying ${library.notation()}: ${e.message}")
//    }
//}