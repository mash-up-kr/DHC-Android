import com.android.build.gradle.LibraryExtension
import com.dhc.buildlogic.util.configureAndroidLibrary
import com.dhc.buildlogic.util.configureCompose
import com.dhc.buildlogic.util.configureFlavor
import com.dhc.buildlogic.util.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

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
            }
        }
    }
}