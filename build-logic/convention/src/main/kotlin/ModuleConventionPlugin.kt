import com.android.build.gradle.LibraryExtension
import com.dhc.buildlogic.util.configureFlavor
import com.dhc.buildlogic.util.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

// Android 의존성이 필요하지만 Application 이나 presentation 이 아닌 모듈에 사용
class ModuleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureFlavor(this)

                defaultConfig.consumerProguardFiles("consumer-rules.pro")
            }
        }
    }
}