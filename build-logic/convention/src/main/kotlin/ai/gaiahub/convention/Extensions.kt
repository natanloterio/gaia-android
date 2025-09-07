package ai.gaiahub.convention
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