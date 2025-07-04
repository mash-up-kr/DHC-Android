import com.android.build.gradle.LibraryExtension
import com.dhc.buildlogic.util.bundle
import com.dhc.buildlogic.util.configureAndroidLibrary
import com.dhc.buildlogic.util.configureCompose
import com.dhc.buildlogic.util.configureFlavor
import com.dhc.buildlogic.util.configureKotlinAndroid
import com.dhc.buildlogic.util.implementation
import com.dhc.buildlogic.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class PresentationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
                configureKotlinAndroid(this)
                configureCompose(this)
                configureFlavor(this)

                defaultConfig.consumerProguardFiles("consumer-rules.pro")

                dependencies {
                    implementation(project(":core:designsystem"))
                    implementation(project(":core:presentation"))
                    implementation(project(":core:common"))
                    implementation(project(":data"))
                    implementation(libs.bundle("coil"))
                }
            }
        }
    }
}