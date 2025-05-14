import com.dhc.buildlogic.util.bundle
import com.dhc.buildlogic.util.implementation
import com.dhc.buildlogic.util.kapt
import com.dhc.buildlogic.util.library
import com.dhc.buildlogic.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-kapt")
                apply("com.google.dagger.hilt.android")
            }

            dependencies {
                implementation(libs.bundle("hilt"))
                kapt(libs.library("hilt.compiler"))
            }
        }
    }
}