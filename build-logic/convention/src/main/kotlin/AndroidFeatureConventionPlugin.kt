import com.novack.dance_tracker.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("dance_tracker.android.library")
                apply("dance_tracker.android.hilt")
            }
            /*extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner =
                        "com.novack.nowinandroid.core.testing.TestRunner"
                }
            }*/

            dependencies {
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:design_system"))
                add("implementation", project(":core:model"))

                add("testImplementation", kotlin("test"))
                add("androidTestImplementation", kotlin("test"))

                // Here we're accessing the compose navigation library
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}
